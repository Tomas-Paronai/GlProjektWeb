package com.emporganizer.models.employee;

import java.sql.Timestamp;

public class Shift {
	private Timestamp enterTime;
	private Timestamp exitTime;
	private int totalHours;
	
	public Shift(Timestamp enterTime, Timestamp exitTime, int totalHours) {
		super();
		this.enterTime = enterTime;
		this.exitTime = exitTime;
		this.totalHours = totalHours;
	}

	public Timestamp getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Timestamp enterTime) {
		this.enterTime = enterTime;
	}

	public Timestamp getExitTime() {
		return exitTime;
	}

	public void setExitTime(Timestamp exitTime) {
		this.exitTime = exitTime;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}
	
}
