/*
*
*   FileUtils
*
*   Copyright (c) 2017 MagRabbit
*   All rights reserved.
*
*   This software is the confidential and proprietary information
*   of MagRabbit.
*
*
*/
package com.brycen.hrm.utils;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class use to convert a string data to a object
 *
 * @author rimnguyen
 * @since 1.0
 */
public class MapperUtils<T> {
	@Autowired
	MessageSource messageSource;
	
	private Class<T> type;

	public MapperUtils(Class<T> type) {
		this.type = type;
	}

	/**
	 * Convert string data to a object.
	 * 
	 * @param data
	 *            string data need convert
	 * @return object convert from string data. If string data format is not as Json
	 *         format @Throw IllegalArgumentException
	 * 
	 */
	public T mapper(String data) {
		ObjectMapper objectMapper = new ObjectMapper();
		T obj = null;
		try {
			obj = objectMapper.readValue(data, type);
		} catch (IOException e) {
			throw new IllegalArgumentException(messageSource.getMessage("format.error", null, null));
		}
		return obj;
	}
}
