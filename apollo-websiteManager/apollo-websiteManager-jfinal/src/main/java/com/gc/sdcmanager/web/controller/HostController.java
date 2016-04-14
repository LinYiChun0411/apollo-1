package com.gc.sdcmanager.web.controller;

import java.util.Objects;

import com.gc.jfinal.ext.render.SpiderRender;
import com.gc.sdcmanager.exception.SpiderException;
import com.gc.sdcmanager.model.Host;
import com.gc.sdcmanager.service.HostService;
import com.gc.sdcmanager.web.interceptor.AuthInterceptor;
import com.gc.sdcmanager.web.interceptor.HostValidator;
import com.jfinal.aop.Before;

@Before(AuthInterceptor.class)
public class HostController extends BaseController {
	public void index() {
		String host = getPara("host");
		Integer status = getParaToInt("status");
		setAttr("recordPage", HostService.service.paginate(host, status, getParaToInt("page", 1), 10));
		keepPara();
		render("list.ftl");
	}

	public void add() {
	}

	@Before(HostValidator.class)
	public void addAction() {
		String host = getPara("host");
		Integer status = getParaToInt("status");
		status = status == null ? 1 : status;
		Host h = new Host();
		h.setHost(host);
		h.setStatus(status);
		HostService.service.save(h);
		render(SpiderRender.success("添加成功"));
	}

	public void resetStatus() {
		Integer id = getParaToInt(0);
		Integer status = getParaToInt(1);
		if (id == null || id == 0 || status == null || (status != 1 && status != 2)) {
			throw new SpiderException("参数不对");
		}
		Host h = HostService.service.findById(id);
		if (h == null) {
			throw new SpiderException("IP配置信息不存在");
		}
		if (!Objects.equals(h.getStatus(), status)) {
			h.setStatus(status);
			HostService.service.update(h);
		}
		render(SpiderRender.success("状态修改成功"));
	}

	public void delete() {
		HostService.service.deleteById(getParaToInt(0));
		render(SpiderRender.success("IP配置信息删除成功"));
	}

	public void edit() {
		setAttr("item", HostService.service.findById(getParaToInt()));
	}

	@Before(HostValidator.class)
	public void update() {
		Host h = getModel(Host.class, "");
		Host old = HostService.service.findById(h.getId());
		if (old == null) {
			throw new SpiderException("IP配置信息不存在");
		}
		old.setHost(h.getHost());
		old.setStatus(h.getStatus());
		HostService.service.update(old);
		render(SpiderRender.success("修改成功"));
	}

}
