/*
*
*   LoginService
*
*   Copyright (c) 2017 MagRabbit
*   All rights reserved.
*
*   This software is the confidential and proprietary information
*   of MagRabbit.
*
*
*/
package com.brycen.hrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.User;
import com.brycen.hrm.repository.UserRepository;
import com.brycen.hrm.common.ResponseResult;
import com.brycen.hrm.entity.Profile;
import com.brycen.hrm.utils.MapperUtils;
/**
 * Login service include functions for login function
 *
 * @author rimnguyen
 * @since 1.0
 */
@Service
public class LoginService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	MessageSource messageSource;
	
	@Autowired 
	UserDetailsService userDetailsService;
	
	
	@Autowired
	PasswordEncoder crypt;



	/**
	 * Login service include for login function
	 * 
	 * @param user
	 *            user form data include user name , password use to login
	 * @return responseEntity. If login fail @throw IllegalArgumentException
	 */
	public ResponseEntity<User> loginTest(User user) {
		User newUser = userRepository.findByUsername(user.getUsername());
//		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (newUser == null) {
			throw new IllegalArgumentException(messageSource.getMessage("login.fail", null, null));
		}
		if (!newUser.getPassword().equals(user.getPassword())) {
			throw new IllegalArgumentException(messageSource.getMessage("login.fail", null, null));
		}
		userDetailsService.loadUserByUsername(user.getUsername());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("con me chung may" + auth.getPrincipal().toString());
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseResult> add(User data) {
//		MapperUtils<User> mapperUtils = new MapperUtils<User>(User.class);
		User addUserForm = data;
		if (userRepository.findByUsername(addUserForm.getUsername()) != null) {
			throw new IllegalArgumentException(messageSource.getMessage("user.is.exist", null, null));
		}
//		validationUtils.validate(addUserForm, messageSource);
		User user = new User();
		user.setUsername(addUserForm.getUsername());
//		user.setPassword(crypt.encode(addUserForm.getPassword()));
		user.setPassword(addUserForm.getPassword());
		user.setStatus(addUserForm.isStatus());
		Profile profile = new Profile();
		profile.setUserID(user.getId());
//		profile.setFirstname(addUserForm.getFirstname());
//		profile.setLastname(addUserForm.getLastname());
//		profile.setAge(addUserForm.getAge());
//		profile.setAddress(addUserForm.getAddress());
//		profile.setEmail(addUserForm.getEmail());
//		if (file != null) {
//			FileUtils.createFile(rootLocation.resolve(file.getOriginalFilename()).toString(), file, messageSource);
//			profile.setAvatar(file.getOriginalFilename());
//		}
//		profile.setGender(addUserForm.getGender());
//		profile.setPhone(addUserForm.getPhone());
		profile.setUser(user);
		user.setProfile(profile);
		userRepository.save(user);
		ResponseResult responseResult = new ResponseResult(HttpStatus.CREATED.value(),
				messageSource.getMessage("add.success", null, null));
		return new ResponseEntity<ResponseResult>(responseResult, HttpStatus.CREATED);
	}

	
	
}
