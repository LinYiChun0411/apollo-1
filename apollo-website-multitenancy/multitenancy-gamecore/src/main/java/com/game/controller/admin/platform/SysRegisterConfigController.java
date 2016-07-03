package com.game.controller.admin.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.SysRegisterConfig;
import com.game.model.vo.RegisterConfigVo;
import com.game.service.SysRegisterConfigService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/registerconf")
public class SysRegisterConfigController extends BaseAdminController {

	@Autowired
	private SysRegisterConfigService srcService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/platform/registerconfig.jsp");
	}

	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		String name = super.$("name");
		Long platform = super.$long("platform");
		RegisterConfigVo rcvo = new RegisterConfigVo();
		rcvo.setName(name);
		rcvo.setPlatform(platform);
		super.render(srcService.getPageConfig(rcvo));
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(SysRegisterConfig config) {
		super.isNotNull(config.getName(), "名称不能为空!");
		super.isNotNull(config.getPlatform(), "注册入口不能为空!");
		super.isNotNull(config.getType(), "属性类型不能为空!");
		srcService.saveConfig(config);
		super.renderSuccess();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		Long srcId = super.$long("id");
		super.isNotNull(srcId, "不存在此属性!");
		srcService.delConfig(srcId);
		super.renderSuccess();
	}

	@RequestMapping(value = "/updStatus")
	@ResponseBody
	public void updStatus() {
		Long srcId = super.$long("id");
		Long status = super.$long("status");
		super.isNotNull(srcId, "不存在此属性!");
		srcService.updStatus(srcId, status);
		super.renderSuccess();
	}
}
