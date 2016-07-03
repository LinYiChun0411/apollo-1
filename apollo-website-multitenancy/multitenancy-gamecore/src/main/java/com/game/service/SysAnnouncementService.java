package com.game.service;

import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.SysAnnouncement;
import com.game.model.vo.AnnouncementVo;

public interface SysAnnouncementService {
	public Page<Map> getPage(AnnouncementVo anctVo);

	public void saveAnnouncement(SysAnnouncement announcement);
	
	public void updStatus(Long ancId, Long status);
	
	public void deleteAnc(Long ancId);
}
