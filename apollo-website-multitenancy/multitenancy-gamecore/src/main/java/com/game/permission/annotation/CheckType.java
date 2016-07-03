package com.game.permission.annotation;

/**
 * 权限优先级别
 * OPEN > FUNCTION > MODULE
 * @author admin
 *
 */
public enum CheckType {
	
	/**
	 *  不需要验证
	 */
	OPEN,
	
	/**
	 * 该功能已经关闭 无法使用
	 */
	CLOSE,
	
	/**
	 * 功能权限  比如访问地址 localhost/project/admin/menu/delMenu.do
	 * 判断有没有这个url的权限
	 */
	FUNCTION, 
	
	/**
	 * 模块权限   比如访问地址 localhost/project/admin/menu/delMenu.do
	 * 判断是否存在 /admin/menu 目录的权限
	 * 对应admin_menu表的 module_path
	 */
	MODULE
}
