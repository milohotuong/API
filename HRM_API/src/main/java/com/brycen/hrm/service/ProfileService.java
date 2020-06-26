package com.brycen.hrm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.brycen.hrm.entity.Profile;
import com.brycen.hrm.entity.User;
import com.brycen.hrm.repository.ProfileRepository;
//import com.magrabbit.pilot.common.ResponseResult;
//import com.magrabbit.pilot.utils.FileUtils;
//import com.magrabbit.pilot.utils.MapperUtils;

@Service
public class ProfileService {
	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	MessageSource messageSource;

	public List<Profile> findPaginated(int page, int size, String sortField) {
		PageRequest paging = PageRequest.of(page, size, Sort.by(sortField));
		Page<Profile> pagedResult = profileRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Profile>();
		}
	}

	public long getNumberOfProfiles() {
		return profileRepository.count();
	}

	/**
	 * 
	 * @param userId
	 * @return profile detail
	 */
	public ResponseEntity<Profile> getProfileDetail(long userId) {
		Profile profile = profileRepository.findById(userId).get();
		if (profile == null) {
			throw new IllegalArgumentException(messageSource.getMessage("profile.get.fail", null, null));
		}
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}
	
	public ResponseEntity<Profile> updateProfile(Profile profile) { 	
//		validationUtils.validate(profile, messageSource);
		if (profileRepository.findById(profile.getUserID()) == null) {
			throw new IllegalArgumentException(messageSource.getMessage("profile.is.not.exist", null, null));
		}
		Profile newProfile = new Profile(profile.getUserID());
		newProfile.setFirstname(profile.getFirstname());
		newProfile.setLastname(profile.getLastname());
		newProfile.setAge(profile.getAge());
		newProfile.setAddress(profile.getAddress());
		newProfile.setEmail(profile.getEmail());
		newProfile.setAvatar(profile.getAvatar());
		newProfile.setSkills(profile.getSkills());
//		if (file != null) {
//			if (profile.getAvatar()!=null)
//				FileUtils.deleteFile(rootLocation.resolve(profile.getAvatar()).toString());
//			FileUtils.createFile(rootLocation.resolve(file.getOriginalFilename()).toString(), file, messageSource);
//			newProfile.setAvatar(file.getOriginalFilename());
//		}
//		else newProfile.setAvatar(profile.getAvatar());
		newProfile.setGender(profile.getGender());
		newProfile.setPhone(profile.getPhone());
		profileRepository.save(newProfile);
//		ResponseResult responseResult = new ResponseResult(HttpStatus.OK.value(),
//				messageSource.getMessage("update.profile.success", null, null));
		return new ResponseEntity<Profile>(newProfile, HttpStatus.OK);

	}
}
