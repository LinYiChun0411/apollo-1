package com.game.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jay.frame.filter.DataCallback;
import org.jay.frame.filter.SysThreadData;
import org.jay.frame.jdbc.model.AbstractUser;

import com.game.exception.NotStationException;
import com.game.model.SysStation;
import com.game.util.WebUtil;

public class SystemDataCallback implements DataCallback {

	public Object initData(SysThreadData threadData) {
		CustomThreadData data = new CustomThreadData();
		HttpServletRequest request = threadData.getRequest();
		HttpSession session = threadData.getSession();

		StringBuffer requestUrl = request.getRequestURL();
		String domain = WebUtil.getDomain(requestUrl.toString());
		if (domain.equals(SystemConfig.CONTROL_DOMAIN)) { // 总控后台
			data.setStationType(StationType.ADMIN);
			threadData.setSysUser((AbstractUser) session.getAttribute(SystemConfig.SESSION_ADMIN_KEY));
			return data;
		}

		SysStation station = WebUtil.getStation(request);
		if (station == null) {
			throw new NotStationException(domain);
		}
		data.setStation(station);
		String url = request.getRequestURI();
		String basePath = request.getContextPath();
		if (url.startsWith(basePath + SystemConfig.AGENT_CONTROL_PATH + "/")) {// 代理后台
			data.setStationType(StationType.AGENT);
			threadData.setSysUser((AbstractUser) session.getAttribute(SystemConfig.SESSION_AGENT_KEY));
		} else if (url.startsWith(basePath + SystemConfig.MOBILE_CONTROL_PATH + "/")) {// 手机端
			data.setStationType(StationType.MOBILE);
			threadData.setSysUser((AbstractUser) session.getAttribute(SystemConfig.SESSION_MEMBER_KEY));
		} else {// 会员前端
			data.setStationType(StationType.MEMBER);
			threadData.setSysUser((AbstractUser) session.getAttribute(SystemConfig.SESSION_MEMBER_KEY));
		}
		return data;
	}

}
