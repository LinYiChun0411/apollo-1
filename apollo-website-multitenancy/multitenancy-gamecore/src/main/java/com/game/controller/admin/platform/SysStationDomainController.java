package com.game.controller.admin.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.SysStationDomain;
import com.game.model.vo.StationVo;
import com.game.service.SysStationDomainService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/domain")
public class SysStationDomainController extends BaseAdminController {

	@Autowired
	private SysStationDomainService sysDomainService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/platform/domain.jsp");
	}

	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		String name = super.$("name");
		String domain = super.$("domain");
		StationVo svo = new StationVo();
		svo.setDomain(domain);
		svo.setName(name);
		super.render(sysDomainService.getPage(svo));
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(SysStationDomain domain) {
		super.isNotNull(domain.getStationId(), "站点不能为空!");
		super.isNotNull(domain.getDomain(), "域名不能为空!");
		super.isNotNull(domain.getStatus(), "状态不能为空!");

		sysDomainService.saveDomain(domain);
		super.renderSuccess();
	}

	@RequestMapping(value = "/open")
	@ResponseBody
	public void open() {

		Long domainId = super.$long("domainId");
		super.isNotNull(domainId, "不存在此域名!");
		sysDomainService.updStatus(domainId, SysStationDomain.STATUS_ENABLE);
		super.renderSuccess();
	}

	@RequestMapping(value = "/close")
	@ResponseBody
	public void close() {
		Long domainId = super.$long("domainId");
		super.isNotNull(domainId, "不存在此域名!");
		sysDomainService.updStatus(domainId, SysStationDomain.STATUS_DISABLE);
		super.renderSuccess();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		Long domainId = super.$long("domainId");
		super.isNotNull(domainId, "不存在此域名!");
		sysDomainService.deleteDomain(domainId);
		super.renderSuccess();
	}
}
