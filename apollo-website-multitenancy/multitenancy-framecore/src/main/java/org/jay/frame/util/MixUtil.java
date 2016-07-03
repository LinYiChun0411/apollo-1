package org.jay.frame.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MixUtil {
	public static Map<Object, Object> toMap(Object[] args) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (int i = 1; i < args.length; i += 2) {
			map.put(args[i - 1], args[i]);
		}
		return map;
	}
	
	public static Map<Object, Object> newHashMap(Object... args) {
		return toMap(args);
	}
	
}
