package com.game.core;

import com.game.model.SysStation;

public class CustomThreadData {
	
	private SysStation station;
	
	public SysStation getStation() {
		return station;
	}

	public void setStation(SysStation station) {
		this.station = station;
	}

	public StationType getStationType() {
		return stationType;
	}

	public void setStationType(StationType stationType) {
		this.stationType = stationType;
	}

	private StationType stationType;
	
}
