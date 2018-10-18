package com.its.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class CodeGenerator {

    static final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);

    //静态变量存储最大值
    public static final AtomicInteger atomicNum = new AtomicInteger();

    /**
     * 初始化设置分组编号最大值
     * @Author  zhangzhi
     * @throws Exception
     * void
     */
    public static void initMaxNum(int maxCode, int defaultCode){
        try{
            if(maxCode <= defaultCode){
                maxCode = defaultCode;
            }
            if(logger.isDebugEnabled()){
                logger.debug("=====初始化编码最大值为：" + maxCode);
            }
            atomicNum.set(maxCode);
        }catch(Exception e){
            logger.error("=====初始化获取编号最大值异常=====",e);
        }
    }

    /**
     * 获取最新分组编号
     * @Author  zhangzhi
     * @return
     * int
     * 注：此方法并没有使用synchronized进行同步，因为共享的编号自增操作是原子操作，线程安全的
     */
    public static String getNewAutoNum(String format){
        String newStrNum = null;
        //线程安全的原子操作，所以此方法无需同步
        int newNum = atomicNum.incrementAndGet();
        if (!"".equals(format) && null != format) {
            //format示例 %05d 数字长度为5位，长度不够数字前面补0
            newStrNum = String.format(format, newNum);
        }else{
            newStrNum = String.valueOf(newNum);
        }
        return newStrNum;
    }
}
