package org.jay.frame;

import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.jay.frame.jdbc.Constants;
import org.jay.frame.jdbc.DBType;
import org.jay.frame.util.PropertiesUtil;
import org.jay.frame.util.SqlUtil;
import org.jay.frame.util.StringUtil;

/**
 * Application Lifecycle Listener implementation class FrameListeners
 *
 */
public class FrameListeners implements ServletContextListener {
	
	Logger log = Logger.getLogger(FrameListeners.class);
	
    public FrameListeners() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	initFrameProperites();
    }
    
	private void initFrameProperites(){
		try{
			PropertiesUtil pu = new PropertiesUtil("frame.properties");
			//Map<String,String> data = pu.readAllProperties();
			String showSql = pu.$("jdbc.show.sql");
			
			FrameProperites.JDBC_SHOW_SQL = "true".equalsIgnoreCase(showSql) ? true : false;
			
			FrameProperites.THREAD_DATA_CALLBACK_CLASS = pu.$("thread.data.callback.class");
			
			FrameProperites.EXCEPTION_HANDLER_CLASS = pu.$("exception.handler.class",FrameProperites.EXCEPTION_HANDLER_CLASS);
			
			
			
			FrameProperites.PAGING_CALLBACK_CLASS = pu.$("paging.callback.class");
			
			FrameProperites.PAGING_PARAM_PAGE_NUMBER = pu.$("paging.param.page.number","pageNo");
			
			FrameProperites.PAGING_PARAM_PAGE_SIZE = pu.$("paging.param.page.size","pageSize");
			
			FrameProperites.PAGING_PAGE_NUMBER_START_FROM_ZERO = pu.$b("paging.page.number.start.from.zero", false);
			
			FrameProperites.PAGING_DEFAULT_PAGE_SIZE = pu.$int("paging.default.page.size", 20);
			
			FrameProperites.PAGING_JSON_TOTAL = pu.$("paging.json.total","total");
			
			FrameProperites.PAGING_JSON_ROWS = pu.$("paging.json.rows","rows");
			
			
			readDbType(pu);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("【classpath:frame.properties读取失败】");
			System.exit(0); 
		}
	}
	
	private void readDbType(PropertiesUtil pu){
		String dbType = pu.$n("db.type"); 
		/* DBType(1-ORACLE,2-SYBASE,3-SQLSERVER,4-DB2,5-MySql,6-Postgre) */
		if("mysql".equalsIgnoreCase(dbType)){
			FrameProperites.DB_TYPE = DBType.MYSQL;
		}else if("postgre".equalsIgnoreCase(dbType)){
			FrameProperites.DB_TYPE = DBType.POSTGRESQL;
		}else if("oracle".equalsIgnoreCase(dbType)){
			FrameProperites.DB_TYPE = DBType.ORACLE;
		}else if("sybase".equalsIgnoreCase(dbType)){
			FrameProperites.DB_TYPE = DBType.SYBASE;
		}else if("sqlserver".equals(dbType)){
			FrameProperites.DB_TYPE = DBType.SQLSERVER;
		}else if("db2".equals(dbType)){
			FrameProperites.DB_TYPE = DBType.DB2;
		}else{
			log.error("【classpath:frame.properties】jdbc.db.name配置项值 必须为 /* DBType(ORACLE,SYBASE,SQLSERVER,DB2,MySql,Postgre) */ ");
			System.exit(0); 
		}
		SqlUtil.DBType = FrameProperites.DB_TYPE.getType();
	}
}
