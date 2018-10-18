package com.its.utils.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyateng on 2014/12/4.
 */
public class JsonDataUtil {
    /**
     * 此方法针对CMS 系统的GlobleResponseBean,pageModel对象 进行转换
     */
    public static <T> List<T> convertToListBean(String jsonString, Class<T> clazz){
        try {
            if (jsonString == null || clazz == null) {
                return null;
            }

            JSONObject result = JSONObject.fromObject(jsonString, JsonValueProcUtil.getConfig(true));
            if (result.getJSONObject("result") == null || result.getJSONObject("result").isEmpty()) {
                return null;
            }
            if(result.getJSONObject("result").getJSONObject("data") == null || result.getJSONObject("result").getJSONObject("data").isEmpty()){
                return null;
            }

            JSONArray jsonArray = result.getJSONObject("result").getJSONObject("data").getJSONArray("datas");
            List<T> list = new ArrayList<>();
            if (jsonArray != null && !jsonArray.isEmpty()) {
                for (Object aJsonArray : jsonArray) {
                    JSONObject next = (JSONObject) aJsonArray;
                    list.add((T) JSONObject.toBean(next, clazz));
                }
                return list;
            }
            return null;


        }catch (Exception e){
            return null;
        }

    }

    public static <T> T convertToBean(String jsonString, Class<T> clazz){
        try {
            if (jsonString == null || clazz == null) {
                return null;
            }
            JSONObject result = JSONObject.fromObject(jsonString, JsonValueProcUtil.getConfig(true));
            if (result.getJSONObject("result") == null || result.getJSONObject("result").isEmpty()) {
                return null;
            }

            JSONObject jsonObj = result.getJSONObject("result").getJSONObject("data");
            if(jsonObj == null || jsonObj.isEmpty()){
                return null;
            }

            if (jsonObj != null && !jsonObj.isEmpty()) {
                return (T) JSONObject.toBean(jsonObj, clazz);
            }
            return null;


        }catch (Exception e){
            return null;
        }
    }
    
    public static void writeResponse(HttpServletResponse response, JSONObject message) {
        PrintWriter out = null;
        try {
            //response.setContentType("application/json;charset=utf-8");
            out = response.getWriter();
            out.write(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
           
        } finally {
            if (null != out) {
                out.close();
                out = null;
            }
        }
    }
}
