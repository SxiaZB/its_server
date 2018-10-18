package com.its.dao.impl;

import j.d.DB;

import java.util.List;

public class UserRDB extends DB {
    @Override
    protected String getPooledConnName() {
        return "its_read";
    }
    /**
     * 获取评论列表
     * @param sql
     * @return
     */
    public static List listCommentUserDao (String sql){
        DB db = new UserRDB();
        db.open();
        db.prepareStatement(sql);
        List list = db.queryRows();
        db.close();
        return list;
    }
}
