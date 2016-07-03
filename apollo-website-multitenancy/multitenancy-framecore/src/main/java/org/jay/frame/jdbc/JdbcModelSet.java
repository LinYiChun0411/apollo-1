package org.jay.frame.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JdbcModelSet {
	static Map<String,JdbcModel>modelMap = new HashMap<String,JdbcModel>();
	
	public static JdbcModel get(Class c){
		String key =  c.getName();
		JdbcModel model = modelMap.get(key);
		if(model == null && modelMap.containsKey(key) == false){
		 	model = AnnotationFactory.getJdbcModel(c);
		 	modelMap.put(key, model);
		}
		return model;
	}
	
}
