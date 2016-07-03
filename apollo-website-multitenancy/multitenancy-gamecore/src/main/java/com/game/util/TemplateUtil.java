package com.game.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.codehaus.groovy.runtime.StringBufferWriter;
import org.jay.frame.exception.ErrorCode;
import org.jay.frame.exception.GenericException;



import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateUtil {
	
	
	public static File TEMPLATE_FOLDER = null;
	//public final static String TEMPLATE_FOLDER_NAME = "template";
	
	public static void createFiles(Map root,String tplName,String folder,String filename){
		createFiles(root, TEMPLATE_FOLDER,tplName, folder,filename);
	}
	/**
	 * 通过模板生成文件
	 * 
	 * @param tplName 模板的文件名
	 * 
	 * @param root 数据源
	 * 
	 * @param folder 生成后存放的文件夹
	 * 
	 * @param filenames 生成文件的名称   
	 * 
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public static void createFiles(Map root,File tplFolder,String tplName,String folder,String filename){
		try{
			Configuration cfg =new Configuration();
			
			cfg.setDirectoryForTemplateLoading(tplFolder);
			Template t = cfg.getTemplate(tplName,"UTF-8");
			File hold = new File(folder);
			if(hold.exists() == false){
				hold.mkdirs();
			}
			File result = new File(folder + "/"+ filename);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(result),"UTF-8")); 
	        t.process(root, out); 
		}catch(Exception e){
			e.printStackTrace();
			throw new GenericException(ErrorCode.SYS_TPL_CREATE_ERROR);
		}
	}
	
	public static String toStr(Map root,String tplName){
		return toStr(root, TEMPLATE_FOLDER,tplName);
	}
	
	public static String toStr(Map root,File tplFolder,String tplName){
		try{
			Configuration cfg =new Configuration();
			cfg.setDirectoryForTemplateLoading(tplFolder);
			Template t = cfg.getTemplate(tplName,"UTF-8");
			StringBuffer sb = new StringBuffer("");
			t.process(root,new StringBufferWriter(sb));
			return sb.toString();
		}catch(Exception e){
			e.printStackTrace();
			throw new GenericException(ErrorCode.SYS_TPL_CREATE_ERROR);
		}
	}
}
