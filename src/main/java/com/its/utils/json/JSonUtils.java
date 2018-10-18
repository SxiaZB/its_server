/**
 *
 */
package com.its.utils.json;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * jackson 转换json和JavaBean
 * @author zhangzhi
 *
 */
public class JSonUtils {

	static ObjectMapper objectMapper;
	/**
	 * 使用泛型方法将json转换成为相应的Javabean对象
	 * @author zhangzhi
	 * @param content  json字符串
	 * @param valueType  JavaBean类型
	 * @return JavaBean对象
	 */
	public static <T> T readValue(String content, Class<T> valueType){
		if(objectMapper == null){
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.readValue(content, valueType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JavaBean转换成json字符串
	 * @author zhangzhi
	 * @param object JavaBean对象
	 * @return  json字符串
	 */
	public static String toJson(Object object){
		if(objectMapper == null){
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
