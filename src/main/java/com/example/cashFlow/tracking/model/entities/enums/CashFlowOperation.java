package com.example.cashFlow.tracking.model.entities.enums;

public enum CashFlowOperation {

	IN("IN"),
	OUT("OUT");
	
	private String code;
	
	private CashFlowOperation(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
//	@Override
//	public static CashFlowOperation valueOf(String code) {
//		switch (code) {
//			case "IN": return CashFlowOperation.IN;
//			case "OUT": return CashFlowOperation.OUT;
//			default: return null;
//		}
//	}
}
