package com.its.dao.impl;

import com.its.utils.json.JSonUtils;
import j.d.DB;

import java.sql.SQLException;

public class PetNoteWDB extends DB {
    @Override
    protected String getPooledConnName() { return "its_write"; }
    private static final String INSERT_SQL=" INSERT INTO pet_note (pet_id,user_id,note_img_url,note_text," +
            " `status`,creater_by,creater_time,lastmodify_by,lastmodify_time)" +
            " VALUES (?,?,?,?,0,?,now(),?,now())";

    /**
     * 添加宠物记录
     * @author 朱志波
     * @param params
     * @return
     */
    public static boolean addPetNoteDao(Object [] params){
        DB db=new PetNoteWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(INSERT_SQL);
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
}
