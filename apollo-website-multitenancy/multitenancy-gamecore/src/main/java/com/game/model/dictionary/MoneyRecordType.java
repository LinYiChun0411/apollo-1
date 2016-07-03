package com.game.model.dictionary;

/**
 * 金额来源类型
 * 
 * @author admin
 *
 */
public enum MoneyRecordType {

	/**
	 * 人工入款
	 */
	DEPOSIT_ARTIFICIAL(1L, true, "人工存款"),
	/**
	 * 人工出款
	 */
	WITHDRAW_ARTIFICIAL(2L, false, "人工取款"),
	/**
	 * 在线入款
	 */
	DEPOSIT_ONLINE(3L, true, "在线存款"),
	/**
	 * 在线出款
	 */
	WITHDRAW_ONLINE(4L, false, "在线取款"),

	/**
	 * 其他
	 */
	OTHER(5L, true, "其他");

	private long type;
	private boolean add;// true : 加款操作 false：扣款操作
	private String name;

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	private MoneyRecordType(long type, boolean add, String name) {
		this.type = type;
		this.add = add;
		this.name = name;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static MoneyRecordType getMoneyRecordType(long val) {
		MoneyRecordType[] types = MoneyRecordType.values();
		for (int i = 0; i < types.length; i++) {
			if (types[i].getType() == val) {
				return types[i];
			}
		}
		return null;
	}
}
