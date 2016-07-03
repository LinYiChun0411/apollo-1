package com.game.controller.admin.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.SysThirdPlatform;
import com.game.model.vo.ThirdPlatVo;
import com.game.service.ThirdPlatformService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/thirdplat")
public class AdminThirdPlatController extends BaseAdminController {

	@Autowired
	private ThirdPlatformService thirdPlatService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/platform/thirdplat.jsp");
	}

	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		// String begin = super.$("beginDatetime");
		// String end = super.$("endDatetime");
		//
		// Date beginDate = DateUtil.toDatetime(begin);
		// Date endDate = DateUtil.toDatetime(end);
		String name = super.$("name");
		ThirdPlatVo tpv = new ThirdPlatVo();
		tpv.setName(name);
		super.render(thirdPlatService.getPage(tpv));
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(SysThirdPlatform thirdPlat) {
		super.isNotNull(thirdPlat.getName(), "第三方平台名称不能为空!");

		thirdPlatService.saveThirdPlat(thirdPlat);
		super.renderSuccess();
	}

	@RequestMapping(value = "/open")
	@ResponseBody
	public void open() {
		Long thirdplatId = super.$long("thirdplatId");
		super.isNotNull(thirdplatId, "不存在此平台!");
		thirdPlatService.updStatus(thirdplatId, SysThirdPlatform.STATUS_ENABLE);
		super.renderSuccess();
	}

	@RequestMapping(value = "/close")
	@ResponseBody
	public void close() {
		Long thirdplatId = super.$long("thirdplatId");
		super.isNotNull(thirdplatId, "不存在此平台!");
		thirdPlatService.updStatus(thirdplatId, SysThirdPlatform.STATUS_DISABLE);
		super.renderSuccess();
	}
}
