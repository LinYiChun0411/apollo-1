package com.game.model.lottery;

import java.util.Date;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name="bc_lottery_time")
public class BcLotteryTime  {
	@Column(name="id",primarykey=true)
	private Long id;
	
	/**
		彩票编码
	*/
	@Column(name="lot_code",length=20)
	private String lotCode;
	
	/**
		期号，每天的期号
	*/
	@Column(name="action_no")
	private Integer actionNo;
	
	/**
		开奖时间
	*/
	@Column(name="action_time")
	private Date actionTime;
	


	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getLotCode(){
		return this.lotCode;
	}
	
	public void setLotCode(String lotCode){
		this.lotCode = lotCode;
	}

	public Integer getActionNo(){
		return this.actionNo;
	}
	
	public void setActionNo(Integer actionNo){
		this.actionNo = actionNo;
	}

	public Date getActionTime(){
		return this.actionTime;
	}
	
	public void setActionTime(Date actionTime){
		this.actionTime = actionTime;
	}
}
