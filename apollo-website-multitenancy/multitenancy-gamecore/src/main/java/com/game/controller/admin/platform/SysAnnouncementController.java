package com.game.controller.admin.platform;

import java.util.Date;

import org.jay.frame.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.SysAnnouncement;
import com.game.model.vo.AnnouncementVo;
import com.game.service.SysAnnouncementService;
import com.game.util.DateUtil;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/announcement")
public class SysAnnouncementController extends BaseAdminController {

	@Autowired
	private SysAnnouncementService sysAnnouncementService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/platform/announcement.jsp");
	}

	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		String name = super.$("name");
		Long type = super.$long("type");
		String begin = super.$("beginDatetime");
		String end = super.$("endDatetime");

		Date beginDate = DateUtil.toDate(begin);
		Date endDate = DateUtil.toDate(end);
		AnnouncementVo ancVo = new AnnouncementVo();
		ancVo.setName(name);
		ancVo.setType(type);
		ancVo.setBegin(beginDate);
		ancVo.setEnd(endDate);
		super.render(sysAnnouncementService.getPage(ancVo));
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public void save() {

		String json = super.$c("data");
		SysAnnouncement announcement = JsonUtil.toBean(json, SysAnnouncement.class);
		sysAnnouncementService.saveAnnouncement(announcement);
		super.renderSuccess();
	}

	@RequestMapping(value = "/updstatus")
	@ResponseBody
	public void updStatus() {

		Long ancId = super.$long("id");
		Long status = super.$long("status");
		super.isNotNull(ancId, "不存在此公告!");
		sysAnnouncementService.updStatus(ancId, status);
		super.renderSuccess();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		Long ancId = super.$long("id");
		super.isNotNull(ancId, "不存在此公告!");
		sysAnnouncementService.deleteAnc(ancId);
		super.renderSuccess();
	}
}
