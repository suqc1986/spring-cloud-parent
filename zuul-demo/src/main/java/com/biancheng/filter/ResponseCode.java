package com.biancheng.filter;

public enum ResponseCode {
	NO_AUTH_CODE(500);
	private int code;
	
	ResponseCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
