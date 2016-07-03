package com.game.model;


import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name="admin_group_menu")
public class AdminGroupMenu  {
	/**
		主键
	*/
	@Column(name="id",primarykey=true)
	private Long id;
	
	/**
		关联admin_group表 id
	*/
	@Column(name="group_id")
	private Long groupId;
	
	/**
		关联admin_menu表id
	*/
	@Column(name="menu_id")
	private Long menuId;
	


	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public Long getGroupId(){
		return this.groupId;
	}
	
	public void setGroupId(Long groupId){
		this.groupId = groupId;
	}

	public Long getMenuId(){
		return this.menuId;
	}
	
	public void setMenuId(Long menuId){
		this.menuId = menuId;
	}
}
