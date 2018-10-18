package com.its.common;

/**
 * 常量配置
 * @author zhangzhi
 * @version 1.0
 */
public class ConstantCommon {

    /**
     * 接口调用状态
     * @author zhangzhi
     */
    public static interface  CallStatus{
        final String SUCCESS = "0"; //远程接口调用成功
        final String FAILURE = "1"; //远程接口调用失败
    }

    /**
     * http请求类型
     * @author  zhangzhi
     */
    public static interface  RestFulMethod{
        final int GET = 0;//http请求类型：GET
        final int POST = 1;//http请求类型：POST
        final int DELETE = 2;//http请求类型：DELETE
        final int PUT = 3;//http请求类型：PUT
    }

    public static  final  int STATUS_DEL = 1;//数据库记录逻辑删除状态值

}
