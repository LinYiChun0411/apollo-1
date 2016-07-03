package com.game.model.lottery;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "bc_lottery_play_group")
public class BcLotteryPlayGroup {
	@Column(name = "id", primarykey = true)
	private Long id;

	/**
	 * 彩种类型，1=系统彩，2=时时彩，3=pk10，4=11选5，5=排列三
	 */
	@Column(name = "lot_type", length = 20)
	private Integer lotType;

	/**
	 * 玩法分组名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 序号
	 */
	@Column(name = "sort_no")
	private Integer sortNo;

	/**
	 * 状态，1=开启，2=关闭
	 */
	@Column(name = "status")
	private Integer status;
	/**
	 * 站点ID
	 */
	@Column(name = "station_id")
	private Long stationId;
	/**
	 * 玩法分组编码，同一彩种中，编码必须唯一
	 */
	@Column(name = "code")
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLotType() {
		return lotType;
	}

	public void setLotType(Integer lotType) {
		this.lotType = lotType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
