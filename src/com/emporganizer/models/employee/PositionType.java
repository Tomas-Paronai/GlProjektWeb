package com.emporganizer.models.employee;

public class PositionType {
	private int possitionID;
	private String positionName;
	
	
	
	public PositionType(){
		
	}
	
	/**
	 * @param possitionID
	 * @param positionName
	 */
	public PositionType(int possitionID, String positionName) {
		
		this.possitionID = possitionID;
		this.positionName = positionName;
	}
	public int getPossitionID() {
		return possitionID;
	}
	public void setPossitionID(int possitionID) {
		this.possitionID = possitionID;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	

}
