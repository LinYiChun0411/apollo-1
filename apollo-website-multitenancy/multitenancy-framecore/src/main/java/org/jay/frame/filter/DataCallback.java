package org.jay.frame.filter;

import org.jay.frame.jdbc.model.AbstractUser;

public interface DataCallback {
	public Object initData(SysThreadData threadData);
}
