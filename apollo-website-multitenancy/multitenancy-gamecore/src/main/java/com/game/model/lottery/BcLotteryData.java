package com.game.model.lottery;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

import java.util.Date;

@Table(name="bc_lottery_data")
public class BcLotteryData  {
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
		号码
	*/
	@Column(name="hao_ma",length=40)
	private String haoMa;
	
	/**
		遗漏
	*/
	@Column(name="ommit",length=500)
	private String ommit;
	
	/**
		投注结束后，开盘时间
	*/
	@Column(name="end_time")
	private Date endTime;
	
	/**
		开奖状态{0未开奖,1都开奖了,2未开奖完, 3,已经取消 4，已经回滚}
	*/
	@Column(name="open_status")
	private Integer openStatus;
	
	/**
		开奖时间
	*/
	@Column(name="open_time")
	private Date openTime;
	
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

	public String getOmmit(){
		return this.ommit;
	}
	
	public void setOmmit(String ommit){
		this.ommit = ommit;
	}

	public Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}

	public Integer getOpenStatus(){
		return this.openStatus;
	}
	
	public void setOpenStatus(Integer openStatus){
		this.openStatus = openStatus;
	}

	public Date getOpenTime(){
		return this.openTime;
	}
	
	public void setOpenTime(Date openTime){
		this.openTime = openTime;
	}

	public Long getStationId(){
		return this.stationId;
	}
	
	public void setStationId(Long stationId){
		this.stationId = stationId;
	}
}
