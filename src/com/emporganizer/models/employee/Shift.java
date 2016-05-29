package com.emporganizer.models.employee;

import java.sql.Timestamp;

public class Shift {
	private Timestamp enterTime;
	private Timestamp exitTime;
	private int totalHours;
	
	public Shift(Timestamp enterTime, Timestamp exitTime) {
		super();
		this.enterTime = enterTime;
		this.exitTime = exitTime;
		totalHours = (int) (exitTime.getTime() - enterTime.getTime())/3600000;
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
