package com.game.controller.admin.platform;

import org.jay.frame.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.SysStation;
import com.game.model.vo.StationVo;
import com.game.service.SysStationService;
@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH+"/station")
public class SysStationController extends BaseAdminController{
	
	@Autowired
	private SysStationService sysStationService;
	
	@RequestMapping("/index")
	public String index(){
		return super.goPage("/page/platform/station.jsp");
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public void list(){
		String name = super.$("name");
		String account = super.$("account");
		StationVo svo = new StationVo();
		svo.setAccount(account);
		svo.setName(name);
		super.render(sysStationService.getPage(svo));
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(SysStation station) {
		super.isNotNull(station.getName(), "站点名称不能为空!");
		super.isNotNull(station.getFloder(), "别名不能为空!");
		super.isNotNull(station.getAccount(), "租户不能为空!");
		
		sysStationService.saveStation(station);
		super.renderSuccess();
	}
	
	@RequestMapping(value = "/open")
	@ResponseBody
	public void open() {
		Long stationId = super.$long("stationId");
		super.isNotNull(stationId, "不存在此站点!");
		sysStationService.updStatus(stationId,SysStation.STATUS_ENABLE);
		super.renderSuccess();
	}
	
	@RequestMapping(value = "/close")
	@ResponseBody
	public void close() {
		Long stationId = super.$long("stationId");
		super.isNotNull(stationId, "不存在此站点!");
		sysStationService.updStatus(stationId,SysStation.STATUS_DISABLE);
		super.renderSuccess();
	}
	
	@RequestMapping(value = "/combo")
	@ResponseBody
	public void combo() {
		super.renderJson(JsonUtil.toJson(sysStationService.getStationCombo()));
	}
}
