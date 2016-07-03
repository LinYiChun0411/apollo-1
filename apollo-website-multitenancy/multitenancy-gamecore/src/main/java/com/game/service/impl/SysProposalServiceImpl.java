package com.game.service.impl;

import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.SysProposalDao;
import com.game.model.SysProposal;
import com.game.model.vo.ProposalVo;
import com.game.service.SysProposalService;

@Repository
public class SysProposalServiceImpl implements SysProposalService {

	@Autowired
	private SysProposalDao sysProposalDao;

	@Override
	public Page<Map> getPage(ProposalVo pvo) {

		return sysProposalDao.getPage(pvo);
	}

	@Override
	public void readProposal(Long proposalId) {
		if (proposalId != null && proposalId != 0) {
			SysProposal saveProposal = sysProposalDao.get(proposalId);
			saveProposal.setStatus(SysProposal.PROPOSAL_STATUS_ENABLE);
			sysProposalDao.save(saveProposal);
		}
	}

	@Override
	public void delProposal(Long proposalId) {
		if (Validator.isNull(proposalId)) {
			throw new GenericException("记录不存在！");
		}
		sysProposalDao.delete(proposalId);
	}
}