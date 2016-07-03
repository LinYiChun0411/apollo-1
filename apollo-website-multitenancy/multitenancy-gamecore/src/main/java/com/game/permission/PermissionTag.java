package com.game.permission;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.jay.frame.util.StringUtil;

import com.game.model.AdminMenu;

public class PermissionTag extends TagSupport {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int doStartTag() throws JspException {
		Map<String, Long> pm = PermissionManager.getAdminPermissionMap();
		long val = StringUtil.toLong(pm.get(url));
		if (val != AdminMenu.STATUS_ENABLED) {
			return SKIP_BODY;
		}
		return EVAL_PAGE;
	}
}