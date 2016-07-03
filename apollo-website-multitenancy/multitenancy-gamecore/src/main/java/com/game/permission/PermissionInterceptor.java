package com.game.permission;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jay.frame.exception.NoLoginException;
import org.jay.frame.exception.PageNotFoundException;
import org.jay.frame.exception.PermissionCheckException;
import org.jay.frame.util.SysUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.game.core.SystemConfig;
import com.game.permission.annotation.CheckType;
import com.game.permission.annotation.NotNeedLogin;
import com.game.permission.annotation.Permission;
import com.game.util.StationUtil;
import com.game.util.WebUtil;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView view)
			throws Exception {
		request.setAttribute("base", request.getContextPath());
		request.setAttribute("pageUrl", request.getRequestURI());
		if (StationUtil.isAdminStation()) {
			request.setAttribute("station", request.getContextPath() + SystemConfig.ADMIN_CONTROL_PATH);
			request.setAttribute("_title", "游戏管理系统");
		} else if (StationUtil.isAgentStation()) {
			request.setAttribute("station", request.getContextPath() + SystemConfig.AGENT_CONTROL_PATH);
			request.setAttribute("_title", "租户平台");
		}

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 处理Permission Annotation，实现方法级权限控制
		HandlerMethod method = (HandlerMethod) handler;
		// 检测路径是否可访问
		checkPathPermission(request);
		// 需登陆才能访问
		NotNeedLogin nnl = method.getMethodAnnotation(NotNeedLogin.class);
		if (nnl != null) {
			return true;
		}
		// 如果为空在表示该方法需要进行登陆验证
		if (!SysUtil.isLogin()) {
			throw new NoLoginException();
		}

		if (StationUtil.isAdminStation()) {// 总控后台
			this.checkAdminPermission(request, method);
		} else if (StationUtil.isAgentStation()) {// 代理后台
			this.checkAgentPermission(request);
		} else if (StationUtil.isMobileStation()) {// 手机端
			this.checkMobilePermission(request);
		} else {// 会员端
			this.checkMemberPermission(request);
		}
		return true;
	}

	/**
	 * 根据当前站点类型判断当前访问路径是否有权限 域名隔离访问路径
	 */
	private void checkPathPermission(HttpServletRequest request) {
		String url = request.getRequestURI();
		String basePath = request.getContextPath();

		// 总控后台只能访问 首页 、/admin/前缀开头的地址
		if (StationUtil.isAdminStation()) {
			// 不允许总控域名问会员前端跟代理后台
			if (!url.equals(basePath + "/") && !url.startsWith(basePath + SystemConfig.ADMIN_CONTROL_PATH + "/")) {
				throw new PageNotFoundException();
			}
		}
		// 不允许盘口站点域名访问总控后台
		if (StationUtil.isAgentStation() || StationUtil.isMemberStation() || StationUtil.isMobileStation()) {
			if (url.startsWith(basePath + SystemConfig.ADMIN_CONTROL_PATH + "/")) {
				throw new PageNotFoundException();
			}
		}
	}

	/**
	 * 检测总控后台权限
	 * 
	 * @param request
	 */
	private void checkAdminPermission(HttpServletRequest request, HandlerMethod method) {
		// root用户，具备后台所有权限
		/**
		 * if(UserUtil.isSuperAdmin()){ return; }
		 **/

		String contentPath = request.getContextPath();
		String path = request.getRequestURI();
		if (!contentPath.equals("/")) { // 去除项目名
			path = path.substring(contentPath.length());
		}
		Permission p = method.getMethodAnnotation(Permission.class);
		String key = null;

		// 若没有Permission标签，则默认该方法只要有模块权限即可访问
		CheckType ct = (p == null) ? CheckType.MODULE : p.value();

		// 功能暂停使用
		if (ct == CheckType.CLOSE) {
			throw new PermissionCheckException("该功能已经暂停使用!");
		}

		// 开放权限，无需验证
		if (ct == CheckType.OPEN) {
			return;
		}

		if (ct == CheckType.MODULE) {
			key = WebUtil.toModuleURL(path);
		} else if (ct == CheckType.FUNCTION) {
			key = WebUtil.toContentURL(path);
		}

		Map<String, Long> pm = PermissionManager.getAdminPermissionMap();
		if (!pm.containsKey(key)) {
			throw new PermissionCheckException("你没有该项操作的权限，请与管理员联系!");
		}
	}

	/**
	 * 获取模块的地址 模块地址
	 * 
	 * @param path
	 * @return
	 */
	private String getModuleURI(String path) {
		return null;
	}

	/**
	 * 检测代理后台权限
	 * 
	 * @param request
	 */
	private void checkAgentPermission(HttpServletRequest request) {

	}

	/**
	 * 检测会员权限
	 * 
	 * @param request
	 */
	private void checkMemberPermission(HttpServletRequest request) {

	}

	/**
	 * 检测手机端权限
	 * 
	 * @param request
	 */
	private void checkMobilePermission(HttpServletRequest request) {

	}

}
