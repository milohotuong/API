package com.brycen.hrm.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.brycen.hrm.entity.Profile;
import com.brycen.hrm.repository.ProfileRepository;
import com.brycen.hrm.utils.FileUtils;

@Service
public class FileService {
	private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
	private static final long MAX_SIZE_OF_FILE = 10;
	@Autowired
	MessageSource messageSource;
	@Autowired
	ProfileRepository profileRepository;

	@Value("${spring.servlet.multipart.location}")
	private Path rootLocation;

	public String upload(MultipartFile mulltipartFile, long userId) {
		String fileContentType = mulltipartFile.getContentType();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String fileName ="";
		if (!contentTypes.contains(fileContentType)) {
			throw new IllegalArgumentException(messageSource.getMessage("image.format.error", null, null));
		}
		if (fileContentType.equals("image/jpeg")) {
			fileName = "user".concat(String.valueOf(userId)).concat(".jpg");
		}
		Path path = Paths.get("C:/Users/milo1/Documents/GitHub/API/HRM_API/src/main/java/com/brycen/hrm/upload/" + fileName);
//		if (mulltipartFile != null) {
//			FileUtils.createFile(rootLocation.resolve(mulltipartFile.getOriginalFilename()).toString(), mulltipartFile,
//					messageSource);
//			return mulltipartFile.getOriginalFilename();
////		profile.setAvatar(mulltipartFile.getOriginalFilename());
//		}
		
		if (mulltipartFile != null) {
			FileUtils.createFile(path.toString(), mulltipartFile,
					messageSource);
			profileRepository.setAvatar(fileName, userId);
			return mulltipartFile.getOriginalFilename();
//		profile.setAvatar(mulltipartFile.getOriginalFilename());
		}

		throw new IllegalArgumentException("concac", new Throwable());
	}

	public byte[] getFile(String fileName) throws IOException {
		Path path = Paths.get("C:/Users/milo1/Documents/GitHub/API/HRM_API/src/main/java/com/brycen/hrm/upload/" + fileName);
		if (path != null) {
			return FileUtils.getImage(fileName, path);
		}
		return null;
	}
}
