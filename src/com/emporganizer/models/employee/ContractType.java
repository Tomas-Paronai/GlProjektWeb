package com.emporganizer.models.employee;

public class ContractType {
	private int contractID;
	private String contractName;
	
	
	
	/**
	 * 
	 */
	public ContractType() {
		
	}
	/**
	 * @param contractID
	 * @param contractName
	 */
	public ContractType(int contractID, String contractName) {
		
		this.contractID = contractID;
		this.contractName = contractName;
	}
	public int getContractID() {
		return contractID;
	}
	public void setContractID(int contractID) {
		this.contractID = contractID;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	
	
	
	

}
