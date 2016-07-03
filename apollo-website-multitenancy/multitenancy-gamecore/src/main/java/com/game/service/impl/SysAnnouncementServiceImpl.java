package com.game.service.impl;

import java.util.Date;
import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.jay.frame.jdbc.model.User;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.SysAnnouncementDao;
import com.game.model.SysAnnouncement;
import com.game.model.vo.AnnouncementVo;
import com.game.service.SysAnnouncementService;
import com.game.util.UserUtil;

@Repository
public class SysAnnouncementServiceImpl implements SysAnnouncementService {

	@Autowired
	private SysAnnouncementDao sysAnnouncementDao;

	@Override
	public Page<Map> getPage(AnnouncementVo ancvo) {

		return sysAnnouncementDao.getPage(ancvo);
	}

	@Override
	public void saveAnnouncement(SysAnnouncement announcement) {

		User admin = UserUtil.getCurrentUser();
		SysAnnouncement saveAnnouncement = null;
		if (announcement.getId() == null) {
			saveAnnouncement = announcement;
			saveAnnouncement.setCreateDatetime(new Date());
			saveAnnouncement.setCreateUserId(admin.getSysUserId());
		} else {
			saveAnnouncement = sysAnnouncementDao.get(announcement.getId());
			saveAnnouncement.setName(announcement.getName());
			saveAnnouncement.setContent(announcement.getContent());
			saveAnnouncement.setBeginDatetime(announcement.getBeginDatetime());
			saveAnnouncement.setEndDatetime(announcement.getEndDatetime());
			saveAnnouncement.setType(announcement.getType());
			saveAnnouncement.setStatus(announcement.getStatus());
		}
		saveAnnouncement.setModifyUserId(admin.getSysUserId());

		sysAnnouncementDao.save(saveAnnouncement);
	}

	@Override
	public void updStatus(Long ancId, Long status) {
		SysAnnouncement anc = sysAnnouncementDao.get(ancId);
		if (anc == null) {
			throw new GenericException("不存在此公告");
		}
		if (!StringUtil.equals(anc.getStatus(), status)) {
			anc.setStatus(status);
			sysAnnouncementDao.save(anc);
		}
	}

	@Override
	public void deleteAnc(Long ancId) {
		sysAnnouncementDao.delete(ancId);
	}
}