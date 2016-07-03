package com.game.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.Page;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.cache.CacheType;
import com.game.cache.CacheUtil;
import com.game.cache.DataReader;
import com.game.dao.AgentMenuDao;
import com.game.model.AgentMenu;
import com.game.model.vo.AgentMenuNode;
import com.game.service.AgentMenuService;

@Repository
public class AgentMenuServiceImpl implements AgentMenuService{
	
	@Autowired
	private AgentMenuDao agentMenuDao;
	
	public AgentMenuNode getMenuCache(final Long agentId) {
		if(agentId == null){
			return null;
		}

		AgentMenuNode node =  CacheUtil.getNull2Set(new DataReader<AgentMenuNode>(){
			public AgentMenuNode getData(){
				List<AgentMenu> menu = null;
				if(agentId.intValue() == 0){
					menu = agentMenuDao.getRootMenu();
				}else{
					menu = agentMenuDao.getAgentMenu(agentId);
				}
				return toMenuNode(menu);
			}
		}, AgentMenuNode.class, CacheType.AGENT_MENU, agentId+"");
		return node;
	}
	
	public AgentMenuNode toMenuNode(List<AgentMenu> menu){
		AgentMenuNode node = new AgentMenuNode();
		Map<Long,List<AgentMenuNode>> nodeMap = new HashMap<Long,List<AgentMenuNode>>();
		for (int i = 0; i < menu.size(); i++) {
			AgentMenu m = menu.get(i);
			long parentId = StringUtil.toLong(m.getParentId());
			List<AgentMenuNode> nodes = nodeMap.get(parentId);
			if(nodes == null){
				nodes = new ArrayList<AgentMenuNode>();
				nodeMap.put(parentId, nodes);
			}
			nodes.add(new AgentMenuNode(m));
		}
		setChildNodes(node,nodeMap,0L);
		return node;
	}
	
	/**
	 * 设置子节点
	 * @param node
	 * @param nodeMap
	 * @param parentId
	 */
	private void setChildNodes(AgentMenuNode node,Map<Long,List<AgentMenuNode>> nodeMap,Long parentId){
	
		List<AgentMenuNode> nodes = nodeMap.get(parentId);
		if(nodes == null || nodes.size() == 0){
			return;
		}
		
		node.setNodes(nodes);
		Collections.sort(nodes);
		for (int i = 0; i < nodes.size(); i++) {
			AgentMenuNode menuNode = nodes.get(i);
			AgentMenu curMenu = menuNode.getCurNode();
			Long id = curMenu.getId();
			setChildNodes(menuNode,nodeMap,id);
		}
	}

	public List<AgentMenu> getAgentPermissionMenu(Long agentId) {
		if(Validator.isNull(agentId)){//root用户
			return agentMenuDao.getRootPermissionMenu();
		}
		return agentMenuDao.getAgentPermissionMenu(agentId);
	}

	public Page<Map> getMenuPage(Long parentId) {
		return agentMenuDao.getMenuPage(parentId);
	}

	@Override
	public void saveMenu(AgentMenu menu) {
		Long menuId = menu.getId();
		long level = menu.getLevel().longValue();
		if(level == 1L){
			menu.setModulePath(null);
			menu.setUrl(null);
		}else if(level == 3L){
			menu.setModulePath(null);
		}
		if(menuId != null){ //保存
			AgentMenu oldMenu = agentMenuDao.get(menuId);
			oldMenu.setIcon(menu.getIcon());
			oldMenu.setLevel(level);
			oldMenu.setModulePath(menu.getModulePath());
			oldMenu.setUrl(menu.getUrl());
			oldMenu.setRemark(menu.getRemark());
			oldMenu.setName(menu.getName());
			oldMenu.setSort(menu.getSort());
			oldMenu.setStatus(menu.getStatus());
			oldMenu.setType(menu.getType());
			agentMenuDao.save(oldMenu);
		}else{//新增
			agentMenuDao.save(menu);
		}
		
	}

	@Override
	public AgentMenuNode getAllLevelThirdMenu() {
		List<AgentMenu>menus = agentMenuDao.getMenuByLevel(3);
		return toMenuNode(menus);
	}

}


