package com.gc.sdcmanager.web.controller;

import com.gc.sdcmanager.service.SportsEventsService;
import com.gc.sdcmanager.web.interceptor.AuthInterceptor;
import com.jfinal.aop.Before;

@Before(AuthInterceptor.class)
public class SportsEventsController extends BaseController {
	public void index() {
		Integer id = getParaToInt("sportsEventsId");
		String gid = getPara("gid");
		String league = getPara("league");
		String teamH = getPara("teamH");
		String teamC = getPara("teamC");
		setAttr("recordPage", SportsEventsService.service.paginate(id, gid, league, teamH, teamC, getParaToInt("page", 1), 50));
		keepPara();
		setAttr("sportsEventsId", id);
		setAttr("gid", gid);
		setAttr("league", league);
		setAttr("teamH", teamH);
		setAttr("teamC", teamC);
		render("list.ftl");
	}

	/*
	public void delete() {
		SportsEventsService.service.deleteById(getParaToInt(0));
		render(SpiderRender.success("删除成功"));
	}
	*/
}
