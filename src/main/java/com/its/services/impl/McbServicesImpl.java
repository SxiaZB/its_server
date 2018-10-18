package com.its.services.impl;

import com.its.dao.impl.MCBpetRDB;
import com.its.dao.impl.PetRDB;
import com.its.entity.User;
import com.its.services.IMcbServices;
import com.its.utils.json.JSonUtils;
import j.m.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class McbServicesImpl implements IMcbServices {
    static final Logger logger = LoggerFactory.getLogger(McbServicesImpl.class);

    /**
     * 获取名宠榜
     * @param user
     * @return
     */
    @Override
    public Result getMcb(User user) {
        Result result = new Result();
        String sql = "SELECT p.pet_id,p.pet_user_img_url,p.pet_name," +
                " p.pet_grade,mp.up_sum,mp.follow_sum,p.follow_name," +
                " CASE WHEN fm.follow_pet_id =p.pet_id THEN 1 ELSE 0 END" +
                " AS follow_status FROM (mcb_pet mp LEFT JOIN pet p" +
                " ON mp.pet_id = p.pet_id)LEFT JOIN follow_map fm" +
                " ON mp.pet_id = fm.follow_pet_id AND" +
                " fm.user_id = '"+ user.getUserId() +"' ORDER BY mp.pet_value DESC LIMIT 20" ;
        logger.info("====McbServicesImpl==== getMcb：sql" + sql);
        List list = MCBpetRDB.listMcbDao(sql);
        logger.info("====McbServicesImpl==== getMcb：list" + JSonUtils.toJson(list));
        result.put("data", list);
        return result;
    }
}
