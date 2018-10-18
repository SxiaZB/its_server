package com.its.dao.impl;

import j.d.DB;

import java.util.List;

public class MCBpetRDB extends DB {
    @Override
    protected String getPooledConnName() {
        return "its_read";
    }

    /**
     * 获取名宠榜
     *
     * @param sql
     * @return
     */
    public static List listMcbDao(String sql) {
        DB db = new MCBpetRDB();
        db.open();
        db.prepareStatement(sql);
        List list = db.queryRows();
        db.close();
        return list;
    }
}
