package com.its.dao.impl;

import com.its.utils.json.JSonUtils;
import j.d.DB;

import java.sql.SQLException;

public class PetWDB extends DB {
    @Override
    protected String getPooledConnName() {
        return "its_write";
    }
    /**
     * 添加宠物
     * @author 朱志波
     * @param params
     * @return
     */
    public static boolean addPetDao(Object [] params,String sql){
        DB db=new PetWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(sql);
            db.setParameters(params);
            long row = db.executeForId();
            db.commit();
            return row > 0;
        } catch (SQLException e) {
            logger.error(JSonUtils.toJson(params),e);
            db.rollback();
        } finally {
            if (db!=null) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 修改宠物
     * @author 朱志波
     * @param sql
     * @return
     */
    public static boolean updataPetDao(String sql){
        DB db=new PetWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(sql);
            int row = db.execute();
            db.commit();
            return row > 0;
        } catch (SQLException e) {
            logger.error(JSonUtils.toJson(sql),e);
            db.rollback();
        } finally {
            if (db!=null) {
                db.close();
            }
        }
        return false;
    }
}
