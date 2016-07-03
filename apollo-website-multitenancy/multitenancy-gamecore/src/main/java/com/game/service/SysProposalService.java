package com.game.service;

import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.vo.ProposalVo;

public interface SysProposalService {
	public Page<Map> getPage(ProposalVo pvo);

	public void readProposal(Long proposalId);

	public void delProposal(Long proposalId);
}
