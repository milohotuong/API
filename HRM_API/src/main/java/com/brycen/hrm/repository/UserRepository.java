package com.brycen.hrm.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.mapping.CrudMethodsSupportedHttpMethods;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}