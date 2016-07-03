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
import com.game.dao.AdminMenuDao;
import com.game.model.AdminMenu;
import com.game.model.vo.AdminMenuNode;
import com.game.service.AdminMenuService;

@Repository
public class AdminMenuServiceImpl implements AdminMenuService{
	
	@Autowired
	private AdminMenuDao adminMenuDao;
	
	public AdminMenuNode getMenuCache(final Long groupId) {
		if(groupId == null){
			return null;
		}

		AdminMenuNode node =  CacheUtil.getNull2Set(new DataReader<AdminMenuNode>(){
			public AdminMenuNode getData(){
				List<AdminMenu> menu = null;
				if(groupId.intValue() == 0){
					menu = adminMenuDao.getRootMenu();
				}else{
					menu = adminMenuDao.getGroupMenu(groupId);
				}
				return toMenuNode(menu);
			}
		}, AdminMenuNode.class, CacheType.ADMIN_GROUP_MENU, groupId+"");
		return node;
	}
	
	public AdminMenuNode toMenuNode(List<AdminMenu> menu){
		AdminMenuNode node = new AdminMenuNode();
		Map<Long,List<AdminMenuNode>> nodeMap = new HashMap<Long,List<AdminMenuNode>>();
		for (int i = 0; i < menu.size(); i++) {
			AdminMenu m = menu.get(i);
			long parentId = StringUtil.toLong(m.getParentId());
			List<AdminMenuNode> nodes = nodeMap.get(parentId);
			if(nodes == null){
				nodes = new ArrayList<AdminMenuNode>();
				nodeMap.put(parentId, nodes);
			}
			nodes.add(new AdminMenuNode(m));
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
	private void setChildNodes(AdminMenuNode node,Map<Long,List<AdminMenuNode>> nodeMap,Long parentId){
	
		List<AdminMenuNode> nodes = nodeMap.get(parentId);
		if(nodes == null || nodes.size() == 0){
			return;
		}
		
		node.setNodes(nodes);
		Collections.sort(nodes);
		for (int i = 0; i < nodes.size(); i++) {
			AdminMenuNode menuNode = nodes.get(i);
			AdminMenu curMenu = menuNode.getCurNode();
			Long id = curMenu.getId();
			setChildNodes(menuNode,nodeMap,id);
		}
	}

	public List<AdminMenu> getGroupPermissionMenu(Long groupId) {
		if(Validator.isNull(groupId)){//root用户
			return adminMenuDao.getRootPermissionMenu();
		}
		return adminMenuDao.getGroupPermissionMenu(groupId);
	}

	public Page<AdminMenu> getMenuPage(Long parentId) {
		return adminMenuDao.getMenuPage(parentId);
	}

	@Override
	public void saveMenu(AdminMenu menu) {
		Long menuId = menu.getId();
		long level = menu.getLevel().longValue();
		if(level == 1L){
			menu.setModulePath(null);
			menu.setUrl(null);
		}else if(level == 3L){
			menu.setModulePath(null);
		}
		if(menuId != null){ //保存
			AdminMenu oldMenu = adminMenuDao.get(menuId);
			oldMenu.setIcon(menu.getIcon());
			oldMenu.setLevel(level);
			oldMenu.setModulePath(menu.getModulePath());
			oldMenu.setUrl(menu.getUrl());
			oldMenu.setRemark(menu.getRemark());
			oldMenu.setName(menu.getName());
			oldMenu.setSort(menu.getSort());
			oldMenu.setStatus(menu.getStatus());
			oldMenu.setType(menu.getType());
			adminMenuDao.save(oldMenu);
		}else{//新增
			adminMenuDao.save(menu);
		}
		
	}

	@Override
	public AdminMenuNode getAllLevelThirdMenu() {
		List<AdminMenu>menus = adminMenuDao.getMenuByLevel(3);
		return toMenuNode(menus);
	}

}


