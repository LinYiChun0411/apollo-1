package com.game.model.lottery;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "bc_station_lottery_play")
public class BcStationLotteryPlay {
	@Column(name = "id", primarykey = true)
	private Long id;

	/**
	 * 站点ID
	 */
	@Column(name = "station_id")
	private Long stationId;

	/**
	 * 状态1=开启，2=关闭
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 玩法id
	 */
	@Column(name = "lot_play_id")
	private Long lotPlayId;

	/**
	 * 序号
	 */
	@Column(name = "sortNO")
	private Integer sortNO;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStationId() {
		return this.stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLotPlayId() {
		return this.lotPlayId;
	}

	public void setLotPlayId(Long lotPlayId) {
		this.lotPlayId = lotPlayId;
	}

	public Integer getSortNO() {
		return sortNO;
	}

	public void setSortNO(Integer sortNO) {
		this.sortNO = sortNO;
	}
}
