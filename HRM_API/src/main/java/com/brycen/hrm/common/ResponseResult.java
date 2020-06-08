/*
*
*   ReponseResult
*
*   Copyright (c) 2017 MagRabbit
*   All rights reserved.
*
*   This software is the confidential and proprietary information
*   of MagRabbit.
*
*
*/
package com.brycen.hrm.common;

/**
 * ReponseResult use to response result to client
 * 
 * @author rimnguyen
 * @since 1.0
 */
public class ResponseResult {
	private int code;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int i) {
		this.code = i;
	}

	public ResponseResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

}
