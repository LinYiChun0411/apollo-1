package com.game.model.lottery;

import org.apache.commons.lang3.StringUtils;

public enum LotteryEnum {
	BJSC("北京赛车"),

	SH11X5("上海11选5"), JX11X5("江西11选5"), SD11X5("山东11选5"), GD11X5("广东11选5"),

	PL3("排列三"), FC3D("福彩3D"),

	CQSSC("重庆时时彩"), XJSSC("新疆时时彩"), TJSSC("天津时时彩"), JXSSC("江西时时彩"),

	FFC("分分彩"), EFC("二分彩"), WFC("五分彩"),

	LHC("六合彩"), PCEGG("PC蛋蛋");

	private String lotName;// 彩票名称

	private LotteryEnum(String lotName) {
		this.lotName = lotName;
	}

	public String getLotName() {
		return lotName;
	}

	public static LotteryEnum getEnum(String name) {
		if (StringUtils.isEmpty(name))
			return null;
		try {
			return LotteryEnum.valueOf(name);
		} catch (Exception e) {
			return null;
		}
	}
}
