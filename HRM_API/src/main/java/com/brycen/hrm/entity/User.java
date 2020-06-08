/*
*
*   User
*
*   Copyright (c) 2017 MagRabbit
*   All rights reserved.
*
*   This software is the confidential and proprietary information
*   of MagRabbit.
*
*
*/
package com.brycen.hrm.entity;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
/**
 * Model of user
 * 
 * @author rimnguyen
 * @since 1.0
 */
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Size(min = 6, max = 20, message = "validator.invalid.username")
	@Column(name = "username", nullable = false, length = 20)
	private String username;

//	@Size(min = 6, max = 1000, message = "validator.invalid.password")
	@Column(name = "password", nullable = false, length = 20)
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = true)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = true)
	private Date dateUpdated;

	@Column(name = "status", nullable = true)
	private boolean status;
	
	@Column(name ="departmentId", nullable = true, length = 2)
	private int departmentId;


	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Profile profile;
	
	@OneToMany (mappedBy = "user", fetch = FetchType.EAGER)
	private Set<UserRole> userRoles;
////	
//	@ManyToOne( fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "departmentId", referencedColumnName = "departmentId")
//	private Department department;
//	
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Bill> bills;
	
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * default constructor
	 */
	public User() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public User(long id) {
		this.id = id;
	}

	/**
	 * get id
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * set value for id
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get username
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set value for username
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * get password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set value for password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Create date created
	 */
	@PrePersist
	protected void onCreate() {
		dateUpdated = dateCreated = new Date();
	}

	/**
	 * Create date updated
	 */
	@PreUpdate
	protected void onUpdate() {
		dateUpdated = new Date();
	}

	/**
	 * Get Profile
	 * 
	 * @return profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Set Profile
	 * 
	 * @param profile
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * Set status
	 * 
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Get status
	 * 
	 * @return
	 */
	public boolean isStatus() {
		return status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

//	public List<Bill> getBills() {
//		return bills;
//	}
//
//	public void setBills(List<Bill> bills) {
//		this.bills = bills;
//	}
	

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
//
//	public Department getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(Department department) {
//		this.department = department;
//	}

}
