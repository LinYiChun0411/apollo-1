package com.game.model.vo;

import java.util.List;

import org.jay.frame.util.MixUtil;

import com.alibaba.fastjson.JSON;
import com.game.model.AdminMenu;

public class AdminMenuNode implements Comparable<AdminMenuNode> {
	
	public static void main(String[] args) {
		AdminMenuNode node = JSON.parseObject("{curNode:{name:'1'},nodes:[{curNode:{name:'2'}},{curNode:{name:'3'}}]}", AdminMenuNode.class);
		String a = JSON.toJSONString(MixUtil.newHashMap("success","true"));
		System.out.println(a);
	}
	
	public AdminMenuNode(){}
	
	public AdminMenuNode(AdminMenu node){
		this.curNode = node;
	}
	/**
	 * 当前菜单
	 */
	private AdminMenu curNode;
	
	/**
	 * 子菜单栏
	 */
	private List<AdminMenuNode> nodes;

	public AdminMenu getCurNode() {
		return curNode;
	}

	public void setCurNode(AdminMenu curNode) {
		this.curNode = curNode;
	}

	public List<AdminMenuNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<AdminMenuNode> nodes) {
		this.nodes = nodes;
	}

	public int compareTo(AdminMenuNode o) {
		return this.getCurNode().getSort().compareTo(o.getCurNode().getSort());
	}
	
}
