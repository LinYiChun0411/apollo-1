package com.gc.sdcmanager.web.controller;

import com.gc.jfinal.ext.render.SpiderRender;
import com.gc.sdcmanager.exception.SpiderException;
import com.gc.sdcmanager.model.User;
import com.gc.sdcmanager.service.UserService;
import com.gc.sdcmanager.web.interceptor.AddUserValidator;
import com.gc.sdcmanager.web.interceptor.AuthInterceptor;
import com.jfinal.aop.Before;

@Before(AuthInterceptor.class)
public class UserController extends BaseController {
	public void index() {
		String username1 = getPara("username1");
		setAttr("recordPage", UserService.service.paginate(username1, getParaToInt("page", 1), 10));
		keepPara();
		render("list.ftl");
	}

	public void add() {
	}

	@Before(AddUserValidator.class)
	public void addAction() {
		String username = getPara("username");
		String password = getPara("password");
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		UserService.service.save(u);
		render(SpiderRender.success("添加成功"));
	}

	public void resetPwd() {
		Integer id = getParaToInt(0);
		if (id == null || id == 0) {
			throw new SpiderException("请选择用户");
		}
		User u = UserService.service.findById(id);
		if (u == null) {
			throw new SpiderException("用户不存在");
		}
		UserService.service.resetPwd(u);
		render(SpiderRender.success("密码重置成功"));
	}

	public void delete() {
		UserService.service.deleteById(getParaToInt());
		render(SpiderRender.success("用户删除成功"));
	}
}
