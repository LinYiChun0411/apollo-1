package org.jay.frame.util;

import com.alibaba.fastjson.JSON;


public class JsonUtil {
	
	public static String toJson(Object o){
		return JSON.toJSONString(o);
	}
	
	public static <T> T toBean(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}
	

}

