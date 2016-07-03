package com.game.core;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jay.frame.util.PropertiesUtil;
import com.game.cache.CacheManager;

import com.game.cache.redis.RedisAPI;
import com.game.util.TemplateUtil;


public class SystemListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		String projectPath = sce.getServletContext().getRealPath("/");
		TemplateUtil.TEMPLATE_FOLDER = new File(projectPath + "/template");
		//启动redis服务器
		openRedisServer();
		//读取系统配置
		initSysConfig();
		//读取缓存配置
		CacheManager.loadConfigMap();
	}
	
	private void openRedisServer(){
		try{
			RedisAPI.initPool();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("【Redis启动失败，服务器停止运行】");
			System.exit(0); 
		}
	}
	
	private void initSysConfig(){
		try{
			PropertiesUtil pu = new PropertiesUtil("application.properties");
			//Map<String,String> data = pu.readAllProperties();
			SystemConfig.CONTROL_DOMAIN = pu.$("sys.control.domain","localhost");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("【classpath:system.properties读取失败】");
			System.exit(0); 
		}
	}
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
