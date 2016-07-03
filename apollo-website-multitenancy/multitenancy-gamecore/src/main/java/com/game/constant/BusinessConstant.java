package com.game.constant;

public interface BusinessConstant {
	public static final String SWITCH_ON = "on";// 开关开启状态－on
	public static final String SWITCH_OFF = "off";// 开关关闭状态－off

	public static final Integer status_normal = 2;// 状态－开启
	public static final Integer status_unable = 1;// 状态－关闭

	/**
	 * 显示时分组，1=时时彩，2=低频彩，3=快开，4=快三，5=11选5，6=香港彩
	 */
	public static final Integer lottery_view_group_ssc = 1;
	public static final Integer lottery_view_group_dpc = 2;
	public static final Integer lottery_view_group_kk = 3;
	public static final Integer lottery_view_group_ks = 4;
	public static final Integer lottery_view_group_11x5 = 5;
	public static final Integer lottery_view_group_xgc = 6;
	/**
	 * 彩种类型，1=系统彩，2=时时彩，3=pk10，4=排列三，5=11选5，6=香港彩，7=PC蛋蛋
	 */
	public static final Integer lottery_type_xtc = 1;
	public static final Integer lottery_type_ssc = 2;
	public static final Integer lottery_type_pk10 = 3;
	public static final Integer lottery_type_pl3 = 4;
	public static final Integer lottery_type_11x5 = 5;
	public static final Integer lottery_type_xgc = 6;
	public static final Integer lottery_type_pcdd = 7;
}
