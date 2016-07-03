package org.jay.frame.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jay.frame.exception.ParameterException;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.JsonUtil;

/**
 * Struts2 Utils类.
 * 
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 */
public class ActionUtil {

	// header 常量定义
	private static final String ENCODING_PREFIX = "encoding:";
	private static final String NOCACHE_PREFIX = "no-cache:";
	private static final String HEADER_CEIPSTATE_FREFIX = "ceipstate:";
	
	/**
	 * response响应头部
	 * 后台成功执行
	 */
	public static final String HEADER_CEIPSTATE_SUCCESS = HEADER_CEIPSTATE_FREFIX + "1";
	/**
	 * response响应头部
	 * 后台执行出现异常
	 */
	public static final String HEADER_CEIPSTATE_ERROR = HEADER_CEIPSTATE_FREFIX + "2"; 
	/**
	 * response响应头部
	 * 后台执行出现业务异常
	 */
	public static final String HEADER_CEIPSTATE_BIZ = HEADER_CEIPSTATE_FREFIX + "3"; 
	
	/**
	 * response响应头部
	 * 未登录异常
	 */
	public static final String HEADER_CEIPSTATE_NO_LOGIN = HEADER_CEIPSTATE_FREFIX + "4"; 
	
	/**
	 * response响应头部
	 * 没有权限
	 */
	public static final String HEADER_CEIPSTATE_NO_PERMISSION = HEADER_CEIPSTATE_FREFIX + "5"; 
	
	private static final boolean NOCACHE_DEFAULT = true;

	protected static Logger logger = Logger.getLogger(ActionUtil.class);


	// 取得Request/Response/Session的简化函数 //

	/**
	 * 取得HttpSession的简化方法.
	 */
	public static HttpSession getSession() {
		return SysUtil.getRequest().getSession();
	}

	/**
	 * 取得HttpRequest的简化方法.
	 */
	public static HttpServletRequest getRequest() {
		try{
			return SysUtil.getRequest();
		}catch(Exception e){
			return null;	
		}
	}

	/**
	 * 取得HttpResponse的简化方法.
	 */
	public static HttpServletResponse getResponse() {
		return SysUtil.getResponse();
	}

	// 绕过jsp/freemaker直接输出文本的函数 //

	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. render("text/plain", "hello", "encoding:UTF-8"); render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:UTF-8", "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",见示例代码. 不设置时默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content, final String[] headers) {
		render(contentType, content, headers, getResponse());
	}
	
	public static void render(final String contentType, final String content, final String[] headers, HttpServletResponse response) {
		try {
			// 分析headers参数
			String encoding = "UTF-8";
			boolean noCache = NOCACHE_DEFAULT;

			if (headers != null && headers.length > 0) {
				for (int i = 0; i < headers.length; i++) {
					String header = headers[i];
					String headerName = StringUtils.substringBefore(header, ":");
					String headerValue = StringUtils.substringAfter(header, ":");

					if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
						encoding = headerValue;
					} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
						noCache = Boolean.getBoolean(headerValue);
					}
					response.setHeader(headerName, headerValue);
					// else
					// throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
				}
			}

			// 设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String[] headers) {
		render("text/plain", text, headers);
	}

	public static void renderText(final String text){
		render("text/plain", text, new String[]{});
	}
	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String[] headers) {
		render("text/html", html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String[] headers) {
		render("text/xml", xml, headers);
	}

	/**
	 * 默认设置了ceipstate:1
	 */
	public static void renderJson(final String string) {
		render("application/json", string, new String[] { HEADER_CEIPSTATE_SUCCESS });
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param string
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String string, final String[] headers) {
		render("application/json", string, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param map
	 *            Map对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */

	/**
	 * 直接输出JSON.
	 * 
	 * @param map
	 *            Map对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Map map) {
		renderJson(map, new String[] { HEADER_CEIPSTATE_SUCCESS });
	}
	

	public static void renderJson(final Map map, final String[] headers) {
		String jsonString = JsonUtil.toJson(map);
		renderJson(jsonString, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param object
	 *            Java对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object object) {
		renderJson(object, new String[] { HEADER_CEIPSTATE_SUCCESS });
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param object
	 *            Java对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object object, final String[] headers) {
		String jsonString = JsonUtil.toJson(object);
		renderJson(jsonString, headers);
	}
	/**
	 * 直接输出JSON.
	 * @param page
	 */
	public static void render(Page page){
		renderJson(page.toJsonStr());
	}
	
	public static boolean isAjaxRequest(HttpServletRequest request){
		if(request == null){
			request = getRequest();
		}
		String header = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equalsIgnoreCase(header) || "Ext.basex".equalsIgnoreCase(header)){
			return true;
		}
		return false;
	}
	/**
	 * 判断是否是内网访问
	 * @return
	 */
	public static boolean isLocal(){
		HttpServletRequest request = ActionUtil.getRequest();
		String name = request.getServerName();
		if(name.indexOf("localhost") > -1 || name.indexOf("192.168") > -1){
			return true;
		}
		return false;
	}
	/**
	 * 输出success:true
	 */
	public static void renderSuccess(){
		renderJson(MixUtil.newHashMap("success",true));
	}
	/**
	 * 输出失败响应
	 * @param msg
	 */
	public static void renderFailure(String msg){
		Map map = MixUtil.newHashMap("success",false);
		if(msg != null){
			map.put("msg", msg);
		}
		renderJson(map);
	}
	/**
	 * 输出失败响应
	 * @param msg
	 */
	public static void renderFailure(){
		renderFailure(null);
	}
	/**
	 * 获取request参数
	 * @param param
	 * @return
	 */
	public String $(String param){
		HttpServletRequest request = getRequest();
		return request.getParameter(param);
	}
	/**
	 * 校验并获取参数
	 * @param param
	 * @return
	 */
	public String $c(String param,String errorMsg){
		String msg = errorMsg == null? "参数["+param+"]值为空" : errorMsg;
		String v  = $(param);
		if(StringUtil.isEmpty(v)){
			throw new ParameterException(msg);
		}
		return v;
	}
	
	/**
	 * 校验并获取参数
	 * @param param
	 * @return
	 */
	public String $c(String param){
		return  $c(param,null);
	}
	/**
	 * 获取request参数
	 * @param param
	 * @return
	 */
	public Long $long(String param){
		String v  = $(param);
		if(v == null){
			return null;
		}
		return StringUtil.toLong(v);
	}
	/**
	 * 校验并获取参数
	 * @param param
	 * @return
	 */
	public long $cl(String param,String errorMsg){
		String msg = errorMsg == null? "参数["+param+"]值为空" : errorMsg;
		String v  = $(param);
		if(v == null){
			throw new ParameterException(msg);
		}
		try{
			return Long.parseLong(v);
		}catch(NumberFormatException e){
			//"参数["+param+"]值["+v+"]无法转换成数字"
			throw new ParameterException("参数格式有误");
		}
	}
	/**
	 *  获取数据参数
	 * @param param
	 * @param splitChar
	 * @return
	 */
	public long[] $la(String param,String splitChar){
		String val =  $(param);
		if(val == null){
			return new long[]{};
		}
		return StringUtil.toLongArray(val, splitChar);
	}
	
	public long $cl(String param){
		return $cl(param,null);
	}
	
	/**
	 * 值为空的时候抛出异常ParameterException
	 * @param v
	 * 			值
	 * @param errorMsg
	 * 				异常消息
	 */
	public void isNotNull(Object v,String errorMsg){
		if(Validator.isNull(v)){
			throw new ParameterException(errorMsg);
		}
	}
}
