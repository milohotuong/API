package com.brycen.hrm.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brycen.hrm.entity.Profile;
import com.brycen.hrm.entity.User;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long>  {
}
