package com.brycen.hrm.repository;

import java.awt.print.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brycen.hrm.entity.Profile;
import com.brycen.hrm.entity.User;
@Transactional
public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long>  {
	@Modifying
	@Query (value = "UPDATE Profile profile SET profile.avatar = :fileName WHERE profile.userID = :userId ")
	public void setAvatar (@Param("fileName")String fileName, @Param("userId")long userId);
}
