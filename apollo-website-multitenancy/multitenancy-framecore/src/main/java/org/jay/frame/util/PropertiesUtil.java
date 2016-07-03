package org.jay.frame.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jay.frame.exception.PropertiesException;

public class PropertiesUtil {

	/**
	 * 配置文件对象
	 */
	private Properties props = null;
	
	/**
	 * 配置项
	 */
	private Map<String, String> configMap = null;
	/**
	 * 配置文件名
	 */
	private String fileName = null;
	/**
	 * 构造函数
	 * 
	 * @param fileName
	 *            配置文件名称
	 * @throws IOException 
	 */
	public PropertiesUtil(String fileName) throws IOException {
		this.fileName = fileName;
		String filePath = getPath(PropertiesUtil.class) + fileName;
		props = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			configMap = this.readAllProperties();
		}finally{
			// 关闭资源
			in.close();
		}
	}

	/**
	 * 根据key值读取配置的值 Jun 26, 2010 9:15:43 PM
	 * 
	 * @param key
	 *            key值
	 * @return key 键对应的值
	 * @throws IOException
	 */
	public String readValue(String key) throws IOException {
		return props.getProperty(key);
	}

	/**
	 * 读取properties的全部信息 Jun 26, 2010 9:21:01 PM
	 * 
	 * @throws FileNotFoundException
	 *             配置文件没有找到
	 * @throws IOException
	 *             关闭资源文件，或者加载配置文件错误
	 * 
	 */
	private Map<String, String> readAllProperties() throws FileNotFoundException, IOException {
		// 保存所有的键值
		Map<String, String> map = new HashMap<String, String>();
		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = props.getProperty(key);
			map.put(key, Property);
		}
		return map;
	}

	/**
	 * 得到某一个类的路径
	 * 
	 * @param name
	 * @return
	 */
	private String getPath(Class name) {
		String strResult = null;
		if (System.getProperty("os.name").toLowerCase().indexOf("window") > -1) {
			strResult = name.getResource("/").toString().replace("file:/", "").replace("%20", " ");
		} else {
			strResult = name.getResource("/").toString().replace("file:", "").replace("%20", " ");
		}
		return strResult;
	}
	
	public Map<String,String> getConfigMap(){
		return this.configMap;
	}

	/**
	 * 从配置文件中读取值
	 * @param key
	 * @return
	 */
	public String $(String key){
		if(!configMap.containsKey(key)){
			return null;
		}
		return StringUtil.trim2Empty(configMap.get(key));
	}
	
	public Integer $int(String key){
		String val = $(key);
		if(val == null){
			return null;
		}
		try{
			return Integer.parseInt(val);
		}catch(Exception e){
			throw new PropertiesException("配置文件[" + this.fileName + "] 配置项：["+key+"]值必须是一个有效数字，当前配置值是：["+val+"]");
		}
	}
	/**
	 * 从配置文件中读取值,若为空则返回nullVal
	 * @param key
	 * @param nullVal
	 * @return
	 */
	public String $(String key,String nullVal){
		String val = $(key);
		if(val == null){
			return nullVal;
		}
		return val;
	}
	
	/**
	 * 从配置文件中读取值，若为空值则会抛出PropertiesException异常
	 * @param key
	 * @return
	 */
	public String $n(String key){
		String val = StringUtil.trim2Null(configMap.get(key));
		if(val == null){
			throw new PropertiesException("配置文件[" + this.fileName + "] 配置项：["+key+"]不能为空");
		}
		return val;
	}
	/**
	 * 从配置文件中读取一个int值，若为空值则会抛出PropertiesException异常
	 * @param key
	 * @return
	 */
	public int $nint(String key){
		String val = $n(key);
		try{
			return Integer.parseInt(val);
		}catch(Exception e){
			throw new PropertiesException("配置文件[" + this.fileName + "] 配置项：["+key+"]值必须是一个有效数字，当前配置值是：["+val+"]");
		}
	}
	/**
	 * 从配置文件中读取一个int值，若读取到的值为空 则返回nullVal
	 * @param key
	 * @param nullVal
	 * @return
	 */
	public int $int(String key,int nullVal){
		String val = $(key);
		if(val == null){
			return nullVal;
		}
		try{
			return Integer.parseInt(val);
		}catch(Exception e){
			throw new PropertiesException("配置文件[" + this.fileName + "] 配置项：["+key+"]值必须是一个有效数字，当前配置值是：["+val+"]");
		}
	}
	
	/**
	 * 从配置文件中读取一个boolean值
	 * @param key
	 * @param nullVal
	 * @return
	 */
	public Boolean $b(String key){
		String val = $(key);
		if(val == null){
			return null;
		}
		if("true".equalsIgnoreCase(val)){
			return true;
		}
		if("false".equalsIgnoreCase(val)){
			return false;
		}
		throw new PropertiesException("配置文件[" + this.fileName + "] 配置项：["+key+"]只能是true、false或空值，当前配置值是：["+val+"]");
	}
	/**
	 * 从配置文件中读取一个boolean值，若读取到的值为空 则返回nullVal
	 * @param key
	 * @param nullVal
	 * @return
	 */
	public boolean $b(String key,boolean nullVal){
		Boolean val = $b(key);
		if(val == null){
			return nullVal;
		}
		return val;
	}
	
	public static void main(String[] args) throws Exception {
		PropertiesUtil pu = new PropertiesUtil("frame.properties");
		System.out.println(pu.$nint("paging.param.default.page.size"));
	}
}
