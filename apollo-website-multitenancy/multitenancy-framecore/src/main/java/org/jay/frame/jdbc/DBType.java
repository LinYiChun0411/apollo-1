package org.jay.frame.jdbc;
/**
 * 数据库类型
 * @author admin
 *
 */
public enum DBType {
	
	ORACLE(1),
	SYBASE(2),
	SQLSERVER(3),
	DB2(4),
	MYSQL(5),
	POSTGRESQL(6);
	
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	private DBType(int type){
		this.type = type;
	}
}
