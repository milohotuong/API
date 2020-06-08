package com.brycen.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.entity.User;
import com.brycen.hrm.service.LoginService;
import com.brycen.hrm.common.ResponseResult;

/**
 * REST endpoint for functions related to test login to login funtion.
 *
 * @author rimnguyen
 * @since 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {
	@Autowired
	LoginService loginService;

	/**
	 * 
	 * @param user
	 *            user form include username and password
	 * @return
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<User> login(@RequestBody User user) {
//		System.out.println("ID: " + user.getId());
		System.out.println("Date: " + user.getDateCreated());
		return loginService.loginTest(user);
	}
	
	
//	@RequestMapping(path = "/add", method = RequestMethod.POST)
//	public @ResponseBody ResponseEntity<ResponseResult> add(@RequestBody User data) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("con me chung may" + auth.getPrincipal().toString());
//		return loginService.add(data);
//	}
}
