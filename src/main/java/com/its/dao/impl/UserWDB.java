package com.its.dao.impl;

import com.its.utils.json.JSonUtils;
import j.d.DB;

import java.sql.SQLException;

public class UserWDB extends DB {
    @Override
    protected String getPooledConnName() {
        return "its_write";
    }

    /**
     * 添加关注
     * @author 朱志波
     * @param params
     * @return
     */
    public static boolean addFollowDao(Object [] params,String sql){
        DB db=new UserWDB();
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
     * 取消关注
     * @author 朱志波
     * @return
     */
    public static boolean delFollowDao(String sql){
        DB db=new UserWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(sql);
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
     * 点赞
     * @author 朱志波
     * @param params
     * @return
     */
    public static boolean addUpDao(Object [] params,String sql){
        DB db=new UserWDB();
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
     * 取消赞
     * @author 朱志波
     * @return
     */
    public static boolean delUpNoteDao(String sql){
        DB db=new UserWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(sql);
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
     * 添加评论
     * @author 朱志波
     * @param params
     * @return
     */
    public static boolean addCommentDao(Object [] params,String sql){
        DB db=new UserWDB();
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
     * 添加收藏
     * @author 朱志波
     * @param params
     * @return
     */
    public static boolean addLoveNoteDao(Object [] params,String sql){
        DB db=new UserWDB();
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
     * 取消收藏
     * @author 朱志波
     * @return
     */
    public static boolean delLoveNoteDao(String sql){
        DB db=new UserWDB();
        db.open();
        try {
            db.beginTrans();
            db.prepareStatement(sql);
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

}
