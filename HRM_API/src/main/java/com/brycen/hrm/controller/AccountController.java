package com.brycen.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.ResponseResult;
import com.brycen.hrm.entity.User;
import com.brycen.hrm.service.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService accountService;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseResult> add(@RequestBody User data) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("con me chung may" + auth.getPrincipal().toString());
		return accountService.add(data);
	}
}