package com.its.dao.impl;

import com.its.utils.json.JSonUtils;
import j.d.DB;

import java.sql.SQLException;

public class MCBpetWDB extends DB {
    @Override
    protected String getPooledConnName() {
        return "its_write";
    }
    private static final String DEL_MCB = "DELETE FROM mcb_pet";
    private static final String UPDATA_MCB = "UPDATE pet p LEFT JOIN mcb_pet mp ON p.pet_id=mp.pet_id" +
            " SET p.pet_value=mp.pet_value,p.pet_grade=mp.pet_level WHERE mp.pet_level is  not  null";

    /**
     * 名宠榜
     * @author 朱志波
     * @return
     */
    public static boolean addMcbDao(String sql){
        DB db=new MCBpetWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(sql);
            long row = db.execute();
            db.commit();
            return row > 0;
        } catch (SQLException e) {
            db.rollback();
        } finally {
            if (db!=null) {
                db.close();
            }
        }
        return false;
    }
    /**
     * 名宠榜清空
     * @author 朱志波
     * @return
     */
    public static boolean delMcbDao(){
        DB db=new MCBpetWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(DEL_MCB);
            int row = db.execute();
            db.commit();
            return row > 0;
        } catch (SQLException e) {
            db.rollback();
        } finally {
            if (db!=null) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 更新宠物表资料
     * @author 朱志波
     * @return
     */
    public static boolean updataPetDao(){
        DB db=new MCBpetWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(UPDATA_MCB);
            long row = db.execute();
            db.commit();
            return row > 0;
        } catch (SQLException e) {
            db.rollback();
        } finally {
            if (db!=null) {
                db.close();
            }
        }
        return false;
    }
}
