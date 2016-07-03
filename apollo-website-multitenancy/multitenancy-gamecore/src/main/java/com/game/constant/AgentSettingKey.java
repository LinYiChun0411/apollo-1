package com.game.constant;

public enum AgentSettingKey {
	
	onoff_zhen_ren_yu_le("真人娱乐开关"),
    onoff_dian_zi_you_yi("电子游艺开关"),
    onoff_du_li_cai_piao("独立彩票系统开关"),
    
    basic_info_lottery_code("配置默认彩种"),
    
    lottery_page_logo_url("彩票玩法页面logo路径"),
    
    lottery_page_version("彩票投注版本号，用于解决js，css升级后浏览器缓存的问题"),
    
    online_customer_service_url("在线客服的url地址"),
    
    
	onoff_chong_zhi_class("转账记录开关。on－开，off－关"),
    onoff_online_pay_class("在线充值记录开关。on－开，off－关"),
    onoff_offers_apply("优惠申请开关。on－开，off－关"),
    onoff_letters("站内信息开关。on－开，off－关"),
    onoff_fan_shui("彩票中心返水开关。on－开，off－关"),

    onoff_live_game("视讯游戏开关。on－开，off－关"),
    onoff_mg_game("mg游戏开关。on－开，off－关"),
    onoff_lottery_game("彩票游戏开关。on－开，off－关"),
    onoff_sports_game("皇冠体育开关。on－开，off－关"),
    onoff_system("网站开关。on－开，off－关"),
    onoff_ag_game("ag游戏开关。on－开，off－关"),
    onoff_bbin_game("bbin游戏开关。on－开，off－关"),
    onoff_liu_he_cai("六合彩开关"),
    
    
    system_maintenance("网站维护原因"),
    site_base_url("推广链接基础地址"),
    default_agent("默认代理"),
    agent_website_url("代理商管理网址"),
    money_start_time("提款、入款开始处理时间"),
    money_end_time("提款、入款结束处理时间"),
    withdraw_min_money("提款最低金额"),
    withdraw_max_money("提款最高金额"),
    withdraw_time_one_day("每天提款次数限制，0=不限制，其他为取款次数"),

    live_transfor_ag_limit("真人AG转换限制"),
    live_transfor_bbin_limit("真人BBIN转换限制"),
    live_transfor_mg_limit("真人MG转换限制"),

    real_trans_limit("真人转换额度限制"),
    consume_rate("取款消费比例设置"),

    basic_info_website_name("网站基础信息－网站名称"),
    basic_info_customer_phone("网站基础信息－客服电话"),
    basic_info_customer_email("网站基础信息－客服邮箱"),
    basic_info_customer_skype("网站基础信息－客服Skype"),
    basic_info_url_phone("配置网站手机端地址"),

    agent_mode("代理设置－代理机制"),
    agent_rebate_max_num("代理设置－最大返点限制"),
    agent_rebate_step("代理设置－上下级返点差额"),
    agent_withdrawals("代理设置－代理后台给下级充值"),

    register_qq_flag("用户注册开启是否开启QQ"),
    register_phone_flag("用户注册开启是否开启phone"),
    register_email_flag("用户注册开启是否开启email"),
    register_cash_passwd_flag("开启取款密码填写"),
    register_realname_flag("开启真实姓名填写"),

    online_customer_start_time("在线客服上班时间"),
    online_customer_end_time("在线客服下班时间"),

    is_syn_real_live_accountno("是否开启真人账户同步注册"),

    first_register_to_give("第一次注册赠送"),

    pay_normal_show("一般存款是否显示，1=显示"),
    pay_quick_show("快速存款是否显示，1=显示"),
    pay_third_show("第三方存款是否显示，1=显示"),

    app_template_name("系统模版目录名称"),
    lottery_hot_stat_num("彩票冷热分析统计期数"),
    sai_che_swf_path("赛车视频路径"),
    onoff_sai_che_swf("开启开奖视频"),

    sports_data_center_accesskey("体育数据中心访问密钥"),
    sports_data_center_url("体育数据中心地址"),

    jiebao_theme_content_color("捷豹彩票配色方案"),
    real_center_api_url("真人中心apiurl"),
    real_center_tid("真人中心 租户id"),
    real_center_tname("租户姓名,用于账户作为前缀"),
    real_center_tid_secret("真人中心 租户秘钥"),
    real_center_trans_publick_key("真人中心 真人中心传输时交互key"),

    pay_tips_deposit_fast("支付提示 快速入款支付说明"),
    pay_tips_deposit_general("支付提示 一般入款支付说明"),
    pay_tips_deposit_third("支付提示 第三方支付说明"),
    model_ffc_generate_haoMa("分分彩开奖模式，rand=随机，pct=中奖百分比，sys=从数据中心推送开奖结果"),
    model_2fc_generate_haoMa("2分彩开奖模式，rand=随机，pct=中奖百分比，sys=从数据中心推送开奖结果"),
    model_5fc_generate_haoMa("5分彩开奖模式，rand=随机，pct=中奖百分比，sys=从数据中心推送开奖结果"),
    sys_lottery_run_percentage("系统彩开奖中奖百分比，开奖模式为中奖百分比后才生效");
	
	private String desc;

	private AgentSettingKey(String _desc) {
		this.desc = _desc;
	}

	public String getDesc() {
		return desc;
	}

	public static AgentSettingKey get(String name) {
		if (name == null)
			return null;
		try {
			return AgentSettingKey.valueOf(name);
		} catch (Exception e) {
			return null;
		}
	}
}
