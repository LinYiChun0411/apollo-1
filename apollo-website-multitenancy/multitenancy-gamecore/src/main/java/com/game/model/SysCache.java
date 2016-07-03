package com.game.model;


import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;


@Table(name="sys_cache")
public class SysCache  {
	/**
		主键
	*/
	@Column(name="id",primarykey=true)
	private Long id;
	
	/**
		缓存名称
	*/
	@Column(name="name",length=100)
	private String name;
	
	/**
		缓存键值
	*/
	@Column(name="key",length=200)
	private String key;
	
	/**
		键值表达式
	*/
	@Column(name="expression",length=200)
	private String expression;
	
	/**
		缓存描述说明
	*/
	@Column(name="remark")
	private String remark;
	
	/**
		缓存的数据类型
	*/
	@Column(name="data_type",length=100)
	private String dataType;
	
	/**
		数据库索引
	*/
	@Column(name="db")
	private Long db;
	
	/**
		缓存过期时间 单位（秒）
	*/
	@Column(name="timeout")
	private Long timeout;
	


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

	public String getKey(){
		return this.key;
	}
	
	public void setKey(String key){
		this.key = key;
	}

	public String getExpression(){
		return this.expression;
	}
	
	public void setExpression(String expression){
		this.expression = expression;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDataType(){
		return this.dataType;
	}
	
	public void setDataType(String dataType){
		this.dataType = dataType;
	}

	public Long getDb(){
		return this.db;
	}
	
	public void setDb(Long db){
		this.db = db;
	}

	public Long getTimeout(){
		return this.timeout;
	}
	
	public void setTimeout(Long timeout){
		this.timeout = timeout;
	}
}