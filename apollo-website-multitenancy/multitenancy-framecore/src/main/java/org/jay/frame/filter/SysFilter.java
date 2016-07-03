package org.jay.frame.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public abstract class SysFilter extends BaseFilter {
	
	protected Log getTimeLog() {
		return _log;
	}

	protected boolean isFilterEnabled() {
		return _filterEnabled;
	}

	protected Log _log = LogFactory.getLog(SysFilter.class);
	
	private boolean _filterEnabled = true;

}