package org.jay.frame.exception;

/**
 * ErrorCode
 * 
 */
public final class ErrorCode {

	public static final String MSG_ERR_LOCATION = "类[{0}]，方法{1}, 行{2}";
	// 基本单位不能删除
	public static final String MSG_ERR_DEL_BASE_UNIT = "msg_err_del_base_unit";
	// "输入的某个字段长度超过了设计的长度，请自行修改数据！";
	public static final String MSG_ERR_FRAME_DATATOOLONG = "输入数据过长";
	// "数据完整性出错！可能原因：主表记录已经被删除，而您正在修改该主表记录下的子表记录！";
	public static final String MSG_ERR_FRAME_DATAINTEGRITYVIOLATION = "err_frame_data_integrity_violation";
	public static final String NULL_POINT_MSG = "NullPointException";
	// 上传文件出现错误，信息是：
	public static final String MSG_ERR_UPLOAD = "err_upload";
	// 系统提示信息：{0}
	public static final String MSG_TIP_SYSTEM_PROMPT = "tip_system_prompt";
	
	public static final String SYS_INPUT_MSG = "sys_input_msg";
	
	//该条数据可能已经被删除
	public static final String GLOBAL_EMPTY_DATA = "global_empty_data";
	
	//密码错误
	public static final String SYS_WRONG_PSW= "sys_wrong_psw";
	//模板生成失败
	public static final String SYS_TPL_CREATE_ERROR = "sys_tpl_create_error";
	//删除{0}条数据,{1}条数据删除失败
	public static final String  SYS_DELETE_SIZE_INFO = "sys_delete_size_info";
	//成功
	public static final String GLOBAL_SUCCESS = "global_success";
	//失败
	public static final String GLOBAL_FAILURE = "global_failure";
	//系统数据不允许删除
	public static final String SYS_COULD_NOT_DEL = "sys_could_not_del";
	//字符串超出最长限制
	public static final String ERROR_LONG_THAN_MAX = "error_max_length";
	//某一个属性为空值 保存model底层可能会抛出该错误
	public static final String ERROR_EMPTY_PROPERTY = "error_empty_property";
	//数据库已经存在相同值的数据
	public static final String ERROR_UNIQUE_PROPERTY = "error_unique_property";
	
}
