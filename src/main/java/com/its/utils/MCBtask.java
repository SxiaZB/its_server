package com.its.utils;

import com.its.common.PetInfo;
import com.its.dao.impl.MCBpetWDB;
import com.its.dao.impl.PetRDB;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import j.u.XMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 */
@Component
public class MCBtask {
    static final Logger log = LoggerFactory.getLogger(MCBtask.class);

    @Scheduled(cron = "0 50 02 ? * *")   //每天凌晨2点50执行一次
    public void mcbTask() {

        log.info("======MCBtask=====:名宠榜更新任务---开始");
        List<XMap> listAll = PetRDB.allPetDao();
        List<XMap> listTime = PetRDB.timePetDao();
        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO mcb_pet (pet_id,up_sum,follow_sum,pet_value60,pet_value,pet_level,creater_time)VALUES ");
        for (int i = 0; listAll.size() > i; i++) {
            XMap xMapA = listAll.get(i);
            XMap xMapB = listTime.get(i);
            long petValue = 10 * xMapA.getInt("follow") + 5 * xMapA.getInt("comment") + xMapA.getInt("up");
            long petValue60 = (long) (1.5 * (10 * xMapB.getInt("follow") + 5 * xMapB.getInt("comment") + xMapB.getInt("up")));
            String petLevel = PetInfo.getLevel(petValue + petValue60);
            sql.append("(" + xMapA.get("pet_id") + ",'" + xMapA.getInt("up") + "','" + xMapA.getInt("follow")
                    + "'," + petValue60 + "," + petValue + ",'" + petLevel + "',NOW()),");
            log.info("======MCBtask=====:名宠榜更新任务---宠物ID" + xMapA.get("pet_id") + "等级更新为" + petLevel);
        }
        String setSQL = sql.toString();
        setSQL = setSQL.substring(0, setSQL.length() - 1);
        log.info("======MCBtask=====:名宠榜更新任务---sql" + setSQL);
        MCBpetWDB.delMcbDao();
        MCBpetWDB.addMcbDao(setSQL);
        MCBpetWDB.updataPetDao();
        log.info("======MCBtask=====:名宠榜更新任务---结束");
    }
}