package com.game.model.lottery;

import java.math.BigDecimal;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "bc_lottery_play")
public class BcLotteryPlay {
	@Column(name = "id", primarykey = true)
	private Long id;

	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "name", length = 10)
	private String name;

	/**
	 * 最大中奖金额
	 */
	@Column(name = "max_bonus_odds")
	private BigDecimal maxBonusOdds;

	/**
	 * 最小中奖金额
	 */
	@Column(name = "min_bonus_odds")
	private BigDecimal minBonusOdds;

	/**
	 * 返水
	 */
	@Column(name = "rakeback")
	private BigDecimal rakeback;

	/**
	 * 状态，1=启用，2=关闭
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 最高注数
	 */
	@Column(name = "max_number")
	private Integer maxNumber;

	/**
	 * 序号
	 */
	@Column(name = "sort_no")
	private Integer sortNo;

	/**
	 * 详细介绍
	 */
	@Column(name = "detail_desc", length = 500)
	private String detailDesc;

	/**
	 * 中奖范例
	 */
	@Column(name = "win_example", length = 500)
	private String winExample;

	/**
	 * 玩法介绍
	 */
	@Column(name = "play_method", length = 500)
	private String playMethod;

	/**
	 * 玩法编码
	 */
	@Column(name = "code", length = 20)
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMaxBonusOdds() {
		return maxBonusOdds;
	}

	public void setMaxBonusOdds(BigDecimal maxBonusOdds) {
		this.maxBonusOdds = maxBonusOdds;
	}

	public BigDecimal getMinBonusOdds() {
		return minBonusOdds;
	}

	public void setMinBonusOdds(BigDecimal minBonusOdds) {
		this.minBonusOdds = minBonusOdds;
	}

	public BigDecimal getRakeback() {
		return rakeback;
	}

	public void setRakeback(BigDecimal rakeback) {
		this.rakeback = rakeback;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public String getWinExample() {
		return winExample;
	}

	public void setWinExample(String winExample) {
		this.winExample = winExample;
	}

	public String getPlayMethod() {
		return playMethod;
	}

	public void setPlayMethod(String playMethod) {
		this.playMethod = playMethod;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
