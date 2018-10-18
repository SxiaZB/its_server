package com.its.dao.impl;

import j.d.DB;

import java.util.List;

public class PetNoteRDB extends DB {
    @Override
    protected String getPooledConnName() { return "its_read"; }


    /**
     * 获取我的列表
     * @param sql
     * @return
     */
    public static List listMYpetNoteDao ( String sql){
        DB db = new PetNoteRDB();
        db.open();
        db.prepareStatement(sql);
        List list = db.queryRows();
        db.close();
        return list;
    }

    /**
     * 获取热门列表
     * @param sql
     * @return
     */
    public static List listRMpetNoteDao ( String sql){
        DB db = new PetNoteRDB();
        db.open();
        db.prepareStatement(sql);
        List list = db.queryRows();
        db.close();
        return list;
    }

    /**
     * 获取关注列表
     * @param sql
     * @return
     */
    public static List listGZpetNoteDao ( String sql){
        DB db = new PetNoteRDB();
        db.open();
        db.prepareStatement(sql);
        List list = db.queryRows();
        db.close();
        return list;
    }

    /**
     * 获取评论列表
     * @param sql
     * @return
     */
    public static List listCommentNoteDao ( String sql){
        DB db = new PetNoteRDB();
        db.open();
        db.prepareStatement(sql);
        List list = db.queryRows();
        db.close();
        return list;
    }
}
