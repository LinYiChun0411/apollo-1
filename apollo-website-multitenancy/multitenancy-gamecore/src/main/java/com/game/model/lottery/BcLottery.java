package com.game.model.lottery;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "bc_lottery")
public class BcLottery {
	/**
	 * 禁用 、关闭
	 */
	public static final Integer STATUS_DISABLE = 1;
	/**
	 * 启用
	 */
	public static final Integer STATUS_ENABLE = 2;
	
	@Column(name = "id", primarykey = true)
	private Long id;

	/**
	 * 彩票名称，中文名称
	 */
	@Column(name = "name", length = 20)
	private String name;

	/**
	 * 彩种状态1=开启，2=关闭
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 彩票编码，如CQSSC,FFC
	 */
	@Column(name = "code", length = 20)
	private String code;

	/**
	 * 开奖时间跟封盘时间差，单位秒
	 */
	@Column(name = "ago")
	private Integer ago;

	/**
	 * 序号
	 */
	@Column(name = "sort_no")
	private Integer sortNo;

	/**
	 * 显示时分组，1=时时彩，2=低频彩，3=快开，4=快三，5=11选5，6=香港彩
	 */
	@Column(name = "view_group")
	private Integer viewGroup;

	/**
	 * 球数
	 */
	@Column(name = "balls")
	private Integer balls;

	/**
	 * 站点ID
	 */
	@Column(name = "station_id")
	private Long stationId;

	/**
	 * 彩种类型，1=系统彩，2=时时彩，3=pk10，4=排列三，5=11选5，6=香港彩，7=PC蛋蛋
	 */
	@Column(name = "type")
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getAgo() {
		return ago;
	}

	public void setAgo(Integer ago) {
		this.ago = ago;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getViewGroup() {
		return viewGroup;
	}

	public void setViewGroup(Integer viewGroup) {
		this.viewGroup = viewGroup;
	}

	public Integer getBalls() {
		return balls;
	}

	public void setBalls(Integer balls) {
		this.balls = balls;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
