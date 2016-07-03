package com.game.service;

public interface AgentSettingService {
	/**
	 * 根据站点配置的参数名获取参数值
	 * 
	 * @param key
	 * @param stationId
	 * @return
	 */
	String getSettingValueByKey(String key, Long stationId);

	/**
	 * 判断站点是否是独立彩票系统
	 * 
	 * @param stationId
	 * @return
	 */
	boolean isDuLiCaiPiao(Long stationId);

	/**
	 * 开启电子娱乐
	 * 
	 * @param stationId
	 * @return
	 */
	boolean dianZiYouYiHadOpened(Long stationId);

	/**
	 * 开启真人娱乐
	 * 
	 * @param stationId
	 * @return
	 */
	boolean zhenRenYuLeHadOpened(Long stationId);

}
