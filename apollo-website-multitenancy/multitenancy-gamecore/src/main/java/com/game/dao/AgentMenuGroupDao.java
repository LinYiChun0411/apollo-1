package com.game.dao;

import java.util.List;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AgentMenuGroup;

@Repository
public class AgentMenuGroupDao extends JdbcUtilImpl<AgentMenuGroup>{
	
	public List<AgentMenuGroup> getAgentPermission(long accountId){
		String sql = "select * from agent_menu_group where agent_id = :accountId ";
		return super.query2Model(sql, MixUtil.newHashMap("accountId",accountId));
	}
	
	
	public void deleteAll(long accountId){
		String sql = " delete from agent_menu_group where agent_id = :accountId ";
		super.update(sql, MixUtil.newHashMap("accountId",accountId));
	}
}
