package org.jay.frame.jdbc.model;

import java.util.Date;
import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.ParentModel;

import com.alibaba.fastjson.annotation.JSONField;

@ParentModel
public abstract class BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = -1727045277099697552L;

	
	private String act;
	/**
	 * 默认值
	 */
	public static final long Flag_False = 0L;  //已经被逻辑删除数据
	public static final long Flag_True = 1L;   //普通数据
	public static final long Flag_SYS = 2L; //不可删除数据
	public static final long Flag_READONLY = 3L;//只读数据
	
	public static final long INIT_VALUE = 0;
	
	//创建时间字段名称
	public static final String CREATE_DATETIME = "CREATE_DATETIME";
	//创建人字段名称
	public static final String CREATE_USER_ID = "CREATE_USER_ID";
	
	@JSONField(name = "flagActive")
	@Column(name = "FLAG_ACTIVE")
	protected Long flagActive;
	
	@JSONField(name = "createUserId")
	@Column(name = CREATE_USER_ID,updatable=false)
	protected Long createUserId;
	
	@JSONField(name = "createDatetime")
	@Column(name = CREATE_DATETIME,updatable=false)
	protected Date createDatetime;
	

	@JSONField(name = "modifyUserId")
	@Column(name = "MODIFY_USER_ID",insertable=false)
	protected Long modifyUserId;
	
	@JSONField(name = "modifyDatetime")
	@Column(name = "MODIFY_DATETIME",insertable=false)
	protected Date modifyDatetime;

	public Long getFlagActive() {
		return this.flagActive;
	}

	public void setFlagActive(Long flagActive) {
		this.flagActive = flagActive;
	}

	public Long getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}
	
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Long getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

}
