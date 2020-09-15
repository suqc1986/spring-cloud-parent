package com.biancheng.pojo;

public class ResponseData {
	String msg;
	Integer code;
	
	
	public ResponseData(String msg, Integer code) {
		super();
		this.msg = msg;
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public static ResponseData fail(String msg, Integer code) {
		return new ResponseData(msg, code);
	}
	
}
