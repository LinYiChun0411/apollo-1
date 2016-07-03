package org.jay.frame.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {
	/**
	 * 获取setter方法
	 * @param clzz
	 * @param name
	 * 			属性名
	 * @param type
	 * @return
	 */
	public static Method getSetMethod(Class clzz,String name,Class type){
		String methodName = StringUtil.getSetMethodName(name);
		try {
			return clzz.getMethod(methodName, type);
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public static Field getField(Class clzz,String fieldName){
		try {
			return clzz.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取getter方法
	 * @param clzz
	 * @param name
	 * 			属性名
	 * @param type
	 * @return
	 */
	public static Method getGetMethod(Class clzz,String name,Class type){
		Boolean isBool = false;
		if(type == boolean.class || type == Boolean.class){
			isBool = true;
		}
		String methodName = StringUtil.getGetMethodName(name, isBool);
		try{
			Method m = clzz.getMethod(methodName);
			return m;
		}catch(Exception e){
			if(isBool == true){//如果boolean类型以is开头取不到，用get开的重新获取一次
				methodName = StringUtil.getGetMethodName(name);
				try {
					Method m = clzz.getMethod(methodName);
					return m;
				} catch (Exception e1) {
					return null;
				}
			}
		}
		return null;
	}
}
