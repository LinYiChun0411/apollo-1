package com.game.util;

import java.util.Map;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.game.controller.CodeCreatorController;
import com.game.service.impl.FrameServiceImpl;


public class SpringUtil {
	/**
	 * 从当前Spring容器中获取bean
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId){
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(beanId);
	}
}
