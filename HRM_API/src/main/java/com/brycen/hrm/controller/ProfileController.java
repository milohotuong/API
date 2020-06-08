package com.brycen.hrm.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.brycen.hrm.entity.Profile;
import com.brycen.hrm.service.ProfileService;
import com.magrabbit.pilot.common.ResponseResult;
import com.brycen.hrm.service.FileService;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("ProfileController")
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	ProfileService profileService;

	@Autowired
	FileService fileService;
	
	@Autowired 
	ServletContext servletContext;
	
	@Value("${spring.servlet.multipart.location}")
	private Path rootLocation;

	@RequestMapping(path = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Profile>> getAll(@RequestParam(value = "page") Integer page,
			@RequestParam(value = "size") Integer size, @RequestParam(value = "sortBy") String sortField) {
		List<Profile> profiles = profileService.findPaginated(page, size, sortField);
		return new ResponseEntity<List<Profile>>(profiles, HttpStatus.OK);
	}

	@RequestMapping(path = "/getNumberOfProfiles", method = RequestMethod.GET)
	public Long getNumberOfProfiles() {
		return profileService.getNumberOfProfiles();
	}

	@RequestMapping(path = "/getProfileDetail", method = RequestMethod.GET)
	public ResponseEntity<Profile> getProfileDetail(@RequestParam(value = "userId", required = true) long userId) {
		return profileService.getProfileDetail(userId);
	}

	@RequestMapping(path = "/uploadAvatar", method = RequestMethod.POST)
	public @ResponseBody String uploadAvatar(
			@RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) {
		return fileService.upload(multipartFile);
	}

	@RequestMapping(path = "/getImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(fileService.getFile("nvn_tan.jpg"), headers, HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * Update Profile
	 * 
	 * @param data
	 *            Json object in string has been send from UI
	 * @param file
	 *            avatar image has been send from UI
	 * @return
	 */
	@RequestMapping(path = "/updateProfile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Profile> update(@RequestBody Profile data) {
		return profileService.updateProfile(data);
	}
}
