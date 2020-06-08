package com.brycen.hrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.ResponseResult;
import com.brycen.hrm.entity.Profile;
import com.brycen.hrm.entity.User;
import com.brycen.hrm.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	MessageSource messageSource;

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
