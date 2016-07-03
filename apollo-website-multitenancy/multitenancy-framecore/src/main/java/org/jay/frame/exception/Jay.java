package org.jay.frame.exception;

import org.apache.log4j.Logger;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;

public class Jay {
	public static void hasText(String str){
		if(StringUtil.isEmpty(str)){
			throw new JayFrameException(JayFrameException.EMPTY_ERROR);
		}
	}
	
	public static void isNotNull(Object obj){
		if(obj == null){
			throw new JayFrameException(JayFrameException.EMPTY_ERROR);
		}
	}
	
}
