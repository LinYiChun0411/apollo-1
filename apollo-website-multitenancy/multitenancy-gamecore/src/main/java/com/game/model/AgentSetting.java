package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;



@Table(name="agent_setting")
public class AgentSetting  {
	@Column(name="id",primarykey=true)
	private Long id;
	
	/**
		唯一标志key值（系统参数名），固定不变，每个key值都有其特定含义
	*/
	@Column(name="key",length=50)
	private String key;
	
	@Column(name="value",length=1000)
	private String value;
	
	@Column(name="description",length=100)
	private String description;
	
	/**
		状态： 2－启用， 1-禁用
	*/
	@Column(name="status")
	private Integer status;
	
	/**
		站点ID
	*/
	@Column(name="station_id")
	private Long stationId;
	


	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getKey(){
		return this.key;
	}
	
	public void setKey(String key){
		this.key = key;
	}

	public String getValue(){
		return this.value;
	}
	
	public void setValue(String value){
		this.value = value;
	}

	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	public Integer getStatus(){
		return this.status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}

	public Long getStationId(){
		return this.stationId;
	}
	
	public void setStationId(Long stationId){
		this.stationId = stationId;
	}
}
