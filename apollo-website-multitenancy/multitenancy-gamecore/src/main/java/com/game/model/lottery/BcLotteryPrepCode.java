package com.game.model.lottery;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

import java.util.Date;

@Table(name="bc_lottery_prep_code")
public class BcLotteryPrepCode  {
	@Column(name="id",primarykey=true)
	private Long id;
	
	/**
		彩票编码
	*/
	@Column(name="lot_code",length=20)
	private String lotCode;
	
	/**
		期号
	*/
	@Column(name="qi_hao",length=20)
	private String qiHao;
	
	/**
		预开奖号码
	*/
	@Column(name="hao_ma",length=40)
	private String haoMa;
	
	/**
		添加时间
	*/
	@Column(name="create_time")
	private Date createTime;
	
	/**
		添加开奖号码的账号
	*/
	@Column(name="operator",length=50)
	private String operator;
	
	/**
		站点ID
	*/
	@Column(name="station_id")
	private Long stationid;
	


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

	public String getQiHao(){
		return this.qiHao;
	}
	
	public void setQiHao(String qiHao){
		this.qiHao = qiHao;
	}

	public String getHaoMa(){
		return this.haoMa;
	}
	
	public void setHaoMa(String haoMa){
		this.haoMa = haoMa;
	}

	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public String getOperator(){
		return this.operator;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}

	public Long getStationid(){
		return this.stationid;
	}
	
	public void setStationid(Long stationid){
		this.stationid = stationid;
	}
}
