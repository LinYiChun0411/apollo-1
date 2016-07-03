package com.game.service.impl;

import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.SysStationDomainDao;
import com.game.model.SysStationDomain;
import com.game.model.vo.StationVo;
import com.game.service.SysStationDomainService;

@Repository
public class SysStationDomainServiceImpl implements SysStationDomainService {

	@Autowired
	private SysStationDomainDao sysStationDomainDao;

	@Override
	public Page<Map> getPage(StationVo svo) {
		return sysStationDomainDao.getPage(svo);
	}

	@Override
	public void saveDomain(SysStationDomain domain) {
		Long domainId = domain.getId();

		if (sysStationDomainDao.isNotUnique(domain, "domain")) {
			throw new GenericException("域名:\"" + domain.getDomain() + "\"已经存在");
		}

		SysStationDomain saveDomain = null;
		if (domainId == null) {
			saveDomain = domain;
		} else {
			saveDomain = sysStationDomainDao.get(domainId);
			saveDomain.setDomain(domain.getDomain());
			saveDomain.setStationId(domain.getStationId());
			saveDomain.setStatus(domain.getStatus());
		}
		sysStationDomainDao.save(saveDomain);
	}

	@Override
	public void updStatus(Long domainId, Long status) {
		SysStationDomain domain = sysStationDomainDao.get(domainId);
		if(domain == null){
			throw new GenericException("该域名不存在");
		}
		if (domain.getStatus() == null ||domain.getStatus().intValue() != status.intValue()) {
			domain.setStatus(status);
			sysStationDomainDao.save(domain);
		}
	}

	@Override
	public void deleteDomain(Long domainId) {
		sysStationDomainDao.delete(domainId);
	}
}