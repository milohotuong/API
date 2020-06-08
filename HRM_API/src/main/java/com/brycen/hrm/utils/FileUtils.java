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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class use to delte a file
 *
 * @author rimnguyen
 * @since 1.0
 */
public class FileUtils {
	private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
	private static final long MAX_SIZE_OF_FILE = 10;
	/**
	 * Delete a file by path
	 * 
	 * @param path
	 *            path of file need delete
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		file.delete();
	}

	/**
	 * Upload a image avatar.
	 * 
	 * @param path
	 *            path where save image
	 * @param image
	 *            image file need upload
	 * @param messageSource
	 *            bean include messages
	 */
	public static void createFile(String path, MultipartFile image, MessageSource messageSource) {
		String fileContentType = image.getContentType();
		if (!contentTypes.contains(fileContentType)) {
			throw new IllegalArgumentException(messageSource.getMessage("image.format.error", null, null));
		}
		long size = image.getSize() / (1024 * 1024);
		if (size > MAX_SIZE_OF_FILE) {
			throw new IllegalArgumentException(messageSource.getMessage("image.too.big", null, null));
		}
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File file = new File(path);
		try {
			inputStream = image.getInputStream();
			if (!file.exists()) {
				file.createNewFile();
			}
			outputStream = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Create file fail!");
		}
	}
	
	/**
	 * 
	 * @param filename
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static byte[] getImage(String filename, Path path) throws IOException {
		InputStream in = Files.newInputStream(path);
		byte[] media = null;
		try {
			media = IOUtils.toByteArray(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return media;
	}
}
