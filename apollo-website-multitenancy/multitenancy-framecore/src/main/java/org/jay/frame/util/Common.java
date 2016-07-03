package org.jay.frame.util;

import org.jay.frame.exception.AppException;
import org.jay.frame.exception.GenericException;
import org.jay.frame.exception.NoLoginException;

public class Common {
	/**
	 * 判断son的是否属于parent的多层级子孙类
	 * @param son
	 * @param parent
	 * @return
	 */
	public static boolean isParent(Class son,Class parent){
		if(son == parent){
			return true;
		}
		do{
			son = son.getSuperclass();
			if(son == parent){
				return true;
			}
		}while(son != Object.class);
	
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(isParent(NoLoginException.class , NoLoginException.class));
	}
}
