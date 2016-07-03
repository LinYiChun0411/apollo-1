package com.game.model;


import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name="admin_menu")
public class AdminMenu  {
	/**
	 * 菜单禁用
	 */
	public static final long STATUS_DISABLED = 1L;
	/**
	 * 菜单启用
	 */
	public static final long STATUS_ENABLED = 2L;
	/**
	 * 菜单隐藏
	 */
	public static final long STATUS_HIDDEN = 3L;
	
	/**
	 * 节点，无任何功能
	 */
	public static final long TYPE_NONE = 1L;
	/**
	 * 页面
	 */
	public static final long TYPE_PAGE = 2L;
	/**
	 * 按钮功能
	 */
	public static final long TYPE_FUNCTION = 3L;
	
	/**
		主键ID
	*/
	@Column(name="id",primarykey=true)
	private Long id;
	
	/**
		菜单名
	*/
	@Column(name="name",length=50)
	private String name;
	
	/**
		功能路径
	*/
	@Column(name="url",length=200)
	private String url;
	
	/**
		上一级id
	*/
	@Column(name="parent_id")
	private Long parentId;
	
	/**
		菜单排序顺序 从小到大
	*/
	@Column(name="sort")
	private Long sort;
	
	/**
		菜单级别
	*/
	@Column(name="level")
	private Long level;
	
	/**
		菜单栏状态 1：禁用 2：启用  3：隐藏（URL可访问）
	*/
	@Column(name="status")
	private Long status;
	/**
		菜单类别  1：无任何功能  2：页面   3：功能
	 */
	@Column(name="type")
	private Long type;
	
	/**
	 	备注
	 */
	@Column(name="remark")
	private String remark;
	
	/**
	 * 模块路径
	 */
	@Column(name="module_path")
	private String modulePath;
	
	@Column(name="icon")
	private String icon;
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}

	public Long getParentId(){
		return this.parentId;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public Long getSort(){
		return this.sort;
	}
	
	public void setSort(Long sort){
		this.sort = sort;
	}

	public Long getLevel(){
		return this.level;
	}
	
	public void setLevel(Long level){
		this.level = level;
	}

	public Long getStatus(){
		return this.status;
	}
	
	public void setStatus(Long status){
		this.status = status;
	}
}