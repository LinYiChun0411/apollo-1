package com.game.model.vo;

import java.util.List;

import org.jay.frame.util.MixUtil;

import com.alibaba.fastjson.JSON;
import com.game.model.AgentMenu;

public class AgentMenuNode implements Comparable<AgentMenuNode> {
	
	public static void main(String[] args) {
		AgentMenuNode node = JSON.parseObject("{curNode:{name:'1'},nodes:[{curNode:{name:'2'}},{curNode:{name:'3'}}]}", AgentMenuNode.class);
		String a = JSON.toJSONString(MixUtil.newHashMap("success","true"));
		System.out.println(a);
	}
	
	public AgentMenuNode(){}
	
	public AgentMenuNode(AgentMenu node){
		this.curNode = node;
	}
	/**
	 * 当前菜单
	 */
	private AgentMenu curNode;
	
	/**
	 * 子菜单栏
	 */
	private List<AgentMenuNode> nodes;

	public AgentMenu getCurNode() {
		return curNode;
	}

	public void setCurNode(AgentMenu curNode) {
		this.curNode = curNode;
	}

	public List<AgentMenuNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<AgentMenuNode> nodes) {
		this.nodes = nodes;
	}

	public int compareTo(AgentMenuNode o) {
		return this.getCurNode().getSort().compareTo(o.getCurNode().getSort());
	}
	
}
