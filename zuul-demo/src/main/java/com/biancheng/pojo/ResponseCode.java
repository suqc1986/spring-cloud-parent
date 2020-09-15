package com.biancheng.pojo;

public enum ResponseCode {
	NO_AUTH_CODE(500), SERVER_ERROR_CODE(400);
	private int code;
	
	ResponseCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
