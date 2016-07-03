package com.game.model.lottery;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "bc_lottery_order")
public class BcLotteryOrder {
	@Column(name = "id", primarykey = true)
	private Long id;
	/**
	 * 订单号
	 */
	@Column(name = "order_id", length = 36)
	private String orderId;

	/**
	 * 会员账号名
	 */
	@Column(name = "account", length = 50)
	private String account;

	/**
	 * 彩票编号
	 */
	@Column(name = "lot_code", length = 20)
	private String lotCode;

	/**
	 * 彩票期号
	 */
	@Column(name = "qi_hao", length = 20)
	private String qiHao;

	/**
	 * 玩法编码
	 */
	@Column(name = "play_code", length = 20)
	private String playCode;

	/**
	 * 站点ID
	 */
	@Column(name = "station_id")
	private Long stationId;

	/**
	 * 购买的号码
	 */
	@Column(name = "hao_ma")
	private String haoMa;

	/**
	 * 购买时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 开奖时间
	 */
	@Column(name = "open_time")
	private Date openTime;

	/**
	 * 购买注数
	 */
	@Column(name = "buy_zhu_shu")
	private Integer buyZhuShu;

	/**
	 * 中奖注数
	 */
	@Column(name = "win_zhu_shu")
	private Integer winZhuShu;

	/**
	 * 购买倍数
	 */
	@Column(name = "multiple")
	private Integer multiple;

	/**
	 * 购买金额
	 */
	@Column(name = "buy_money")
	private BigDecimal buyMoney;

	/**
	 * 中奖金额
	 */
	@Column(name = "win_money")
	private BigDecimal winMoney;

	/**
	 * 状态 1等待开奖 2已中奖 3未中奖 4撤单 5派奖回滚成功 6回滚异常的 7开奖异常
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 模式 Y元 J角 F分
	 */
	@Column(name = "model", length = 1)
	private String model;

	/**
	 * 是否继续 1中奖撤单 2中奖继续 3非追号
	 */
	@Column(name = "zhui_hao")
	private Integer zhuiHao;

	/**
	 * 会员返水状态 （1，还未返水 2，已经返水 3，返水已经回滚 4,返水已到账 ）
	 */
	@Column(name = "roll_back_status")
	private Integer rollBackStatus;

	/**
	 * 会员返水金额以 元为最小单位
	 */
	@Column(name = "roll_back_money")
	private BigDecimal rollBackMoney;

	/**
	 * 返水比率(0-100)%
	 */
	@Column(name = "roll_back_rate")
	private BigDecimal rollBackRate;

	/**
	 * 返水策略描述
	 */
	@Column(name = "roll_back_desc", length = 200)
	private String rollBackDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}

	public String getQiHao() {
		return qiHao;
	}

	public void setQiHao(String qiHao) {
		this.qiHao = qiHao;
	}

	public String getPlayCode() {
		return playCode;
	}

	public void setPlayCode(String playCode) {
		this.playCode = playCode;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getHaoMa() {
		return haoMa;
	}

	public void setHaoMa(String haoMa) {
		this.haoMa = haoMa;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Integer getBuyZhuShu() {
		return buyZhuShu;
	}

	public void setBuyZhuShu(Integer buyZhuShu) {
		this.buyZhuShu = buyZhuShu;
	}

	public Integer getWinZhuShu() {
		return winZhuShu;
	}

	public void setWinZhuShu(Integer winZhuShu) {
		this.winZhuShu = winZhuShu;
	}

	public Integer getMultiple() {
		return multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

	public BigDecimal getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(BigDecimal buyMoney) {
		this.buyMoney = buyMoney;
	}

	public BigDecimal getWinMoney() {
		return winMoney;
	}

	public void setWinMoney(BigDecimal winMoney) {
		this.winMoney = winMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getZhuiHao() {
		return zhuiHao;
	}

	public void setZhuiHao(Integer zhuiHao) {
		this.zhuiHao = zhuiHao;
	}

	public Integer getRollBackStatus() {
		return rollBackStatus;
	}

	public void setRollBackStatus(Integer rollBackStatus) {
		this.rollBackStatus = rollBackStatus;
	}

	public BigDecimal getRollBackMoney() {
		return rollBackMoney;
	}

	public void setRollBackMoney(BigDecimal rollBackMoney) {
		this.rollBackMoney = rollBackMoney;
	}

	public BigDecimal getRollBackRate() {
		return rollBackRate;
	}

	public void setRollBackRate(BigDecimal rollBackRate) {
		this.rollBackRate = rollBackRate;
	}

	public String getRollBackDesc() {
		return rollBackDesc;
	}

	public void setRollBackDesc(String rollBackDesc) {
		this.rollBackDesc = rollBackDesc;
	}

}
