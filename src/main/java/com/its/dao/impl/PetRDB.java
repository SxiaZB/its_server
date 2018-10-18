package com.its.dao.impl;

import j.d.DB;
import j.u.XMap;

import java.util.List;

public class PetRDB extends DB {
    @Override
    protected String getPooledConnName() {
        return "its_read";
    }

    /**
     * 获取宠物列表
     *
     * @param sql
     * @return
     */
    public static List listPetDao(String sql) {
        DB db = new PetRDB();
        db.open();
        db.prepareStatement(sql);
        List list = db.queryRows();
        db.close();
        return list;
    }

    /**
     * 获取宠物数
     *
     * @param sql
     * @return
     */
    public static String petSumDao(String sql) {
        DB db = new PetRDB();
        db.open();
        db.prepareStatement(sql);
        List list = db.queryRows();
        String sum = ((XMap) list.get(0)).getInt("pet_sum") + 1 + "";
        db.close();
        return sum;
    }

    /**
     * 名宠榜定时任务获取所有宠物热度值
     *
     * @return
     */
    public static List<XMap> allPetDao() {
        String sql = "SELECT p.pet_id,IFNULL(s1.follow_sum,0)AS follow,IFNULL(s2.up_sum,0)AS up," +
                "IFNULL(s3.comment_sum,0)AS `comment` FROM" +
                " ((pet p LEFT JOIN " +
                "(SELECT fm.follow_pet_id,COUNT(1)AS follow_sum FROM follow_map fm GROUP BY fm.follow_pet_id) s1" +
                " ON p.pet_id=s1.follow_pet_id)LEFT JOIN (SELECT pn.pet_id ,COUNT(1) AS up_sum FROM up_note_map unm" +
                " LEFT JOIN pet_note pn ON unm.pet_note_id=pn.pet_note_id GROUP BY pn.pet_id) s2 ON p.pet_id=s2.pet_id)" +
                "LEFT JOIN(SELECT pn.pet_id ,COUNT(1) AS comment_sum FROM comment_note_map cnm LEFT JOIN pet_note pn" +
                " ON cnm.pet_note_id=pn.pet_note_id GROUP BY pn.pet_id) s3 ON p.pet_id=s3.pet_id ORDER BY p.pet_id ASC";
        DB db = new PetRDB();
        db.open();
        db.prepareStatement(sql);
        List<XMap> list = db.queryRows();
        db.close();
        return list;
    }

    /**
     * 近60天的热度值
     * @return
     */
    public static List<XMap> timePetDao() {
        String sql = "SELECT p.pet_id,IFNULL(s1.follow_sum,0)AS follow,IFNULL(s2.up_sum,0)AS up," +
                "IFNULL(s3.comment_sum,0)AS `comment` FROM ((pet p LEFT JOIN" +
                " (SELECT fm.follow_pet_id,COUNT(1)AS follow_sum FROM follow_map fm" +
                " WHERE fm.creater_time>=date_sub(now(), interval 60 day) GROUP BY fm.follow_pet_id)" +
                " s1 ON p.pet_id=s1.follow_pet_id)LEFT JOIN (SELECT pn.pet_id ,COUNT(1)" +
                " AS up_sum FROM up_note_map unm LEFT JOIN pet_note pn ON unm.pet_note_id=pn.pet_note_id" +
                " WHERE unm.creater_time>=date_sub(now(), interval 60 day) GROUP BY pn.pet_id) s2" +
                " ON p.pet_id=s2.pet_id)LEFT JOIN(SELECT pn.pet_id ,COUNT(1)" +
                " AS comment_sum FROM comment_note_map cnm LEFT JOIN pet_note pn" +
                " ON cnm.pet_note_id=pn.pet_note_id WHERE cnm.creater_time>=date_sub(now(), interval 60 day)" +
                " GROUP BY pn.pet_id) s3 ON p.pet_id=s3.pet_id ORDER BY p.pet_id ASC";
        DB db = new PetRDB();
        db.open();
        db.prepareStatement(sql);
        List<XMap> list = db.queryRows();
        db.close();
        return list;
    }
}
