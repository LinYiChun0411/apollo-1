package org.jay.frame.filter;



public class ThreadVariable {

	private static final long serialVersionUID = -6663234393530363486L;

	private static ThreadLocal<SysThreadData> dataThread = new ThreadLocal<SysThreadData>();
	
	/**
	 * 初始化赋值
	 * @param data
	 */
	public static void set(SysThreadData data){
		dataThread.set(data);
	}
	
	public static SysThreadData get(){
		return dataThread.get();
	}
	/**
	 * 清除
	 */
	public static void clear(){
		dataThread.set(null);
		dataThread.remove();
	}
}
