package com.its.utils;

import com.its.common.ConstantCommon;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 构造Http请求的帮助类
 * Created by zhangzhi
 */
public class HttpCallUtils {

    static final Logger logger = LoggerFactory.getLogger(HttpCallUtils.class);


    /**
     * 进行Http协议请求
     * @param url  请求的url
     * @param methodType 请求方式
     * @param params 请求的参数Map
     * @return 请求返回的ResponseBean
     */

    public static JSONObject doRequestHttpWithParams(String url, int methodType, Map<String, String> params){
        HttpRequestBase httpMethod = getHttpMethod(url, methodType);
        setHttpParameters(httpMethod, methodType, params);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonObj = new JSONObject();
        try {
            HttpResponse response = httpClient.execute(httpMethod);
            StatusLine sl = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            jsonObj.put("result", EntityUtils.toString(entity));
            jsonObj.put("statusCode",String.valueOf(sl.getStatusCode()));
            jsonObj.put("success", ConstantCommon.CallStatus.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage());
            jsonObj.put("success", ConstantCommon.CallStatus.FAILURE);
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return jsonObj;
    }

    /**
     * 设置请求参数
     * @param httpMethod 请求对象
     * @param methodType 请求类型
     * @param params 请求参数
     */
    private static void setHttpParameters(HttpRequestBase httpMethod, int methodType, Map<String, String> params){
        if(params == null || params.size() == 0){
            return;
        }
        switch (methodType) {
            case ConstantCommon.RestFulMethod.POST:
                HttpPost postMethod = (HttpPost) httpMethod;
                List<NameValuePair> postParam = new ArrayList<NameValuePair>();
                for(Map.Entry<String, String> en : params.entrySet()){
                    postParam.add(new BasicNameValuePair(en.getKey(), en.getValue()));
                }
                try {
                    postMethod.setEntity(new UrlEncodedFormEntity(postParam));
                } catch (UnsupportedEncodingException ignored) {
                    logger.error(ignored.getMessage());
                }
                break;
            case ConstantCommon.RestFulMethod.GET:
                HttpGet getMethod = (HttpGet) httpMethod;
                StringBuilder sb = new StringBuilder("?");
                for(Map.Entry<String, String> en : params.entrySet()){
                    sb.append(en.getKey());
                    sb.append("=");
                    try {
                        sb.append(URLEncoder.encode(en.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException ignored) {
                        logger.error(ignored.getMessage());
                    }
                }
                try {
                    getMethod.setURI(new URI(getMethod.getURI().toString() + sb.toString()));
                } catch (URISyntaxException ignored) {
                    logger.error(ignored.getMessage());
                }
                break;
            case ConstantCommon.RestFulMethod.DELETE:
                //HttpDelete delMethod = (HttpDelete) httpMethod;
                break;
            case ConstantCommon.RestFulMethod.PUT:
                //HttpPut putMethod = (HttpPut) httpMethod;
                break;
            default:
                break;
        }
    }

    /**
     * 获取请求对象
     * @param url   请求地址
     * @param methodType 请求类型
     * @return 返回一个HttpRequest对象
     */
    private static HttpRequestBase getHttpMethod(String url, int methodType){
        HttpRequestBase httpMethod = null;
        switch (methodType) {
            case ConstantCommon.RestFulMethod.POST:
                // 获取方法为POST的http请求
                httpMethod = new HttpPost(url);
                break;
            case ConstantCommon.RestFulMethod.GET:
                // 获取方法为GET的http请求
                httpMethod = new HttpGet(url);
                break;
            case ConstantCommon.RestFulMethod.DELETE:
                // 获取方法为DELETE的http请求
                httpMethod = new HttpDelete(url);
                break;
            case ConstantCommon.RestFulMethod.PUT:
                // 获取方法为Put的http请求
                httpMethod = new HttpPut(url);
                break;
            default:
                break;
        }
        return httpMethod;
    }
}
