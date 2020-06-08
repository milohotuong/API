/*
*
*   Profile
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.websocket.ClientEndpoint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;
import com.fasterxml.jackson.annotation.JsonIgnore;


import org.hibernate.annotations.Parameter;

/**
 * Model of profile
 * 
 * @author rimnguyen
 * @since 1.0
 */
@Entity
@Table(name = "profile")
public class Profile {
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "user_id", unique = true, nullable = false)
	private long userID;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;

	@Size(min = 2, max = 30, message = "validator.invalid.firstname")
	@Column(name = "firstname", nullable = true, length = 30)
	private String firstname;

	@Size(min = 2, max = 30, message = "validator.invalid.lastname")
	@Column(name = "lastname", nullable = true, length = 30)
	private String lastname;

	@Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{profile.email.regex}")
	private String email;

	@Column(name = "age", nullable = true)
//	@Min(value = 18, message = "validator.invalid.age")
	private int age;

	@Column(name = "gender", nullable = true)
	@Range(min = 0, max = 2, message = "validator.invalid.gender")
	private byte gender;

	@Column(name = "phone", nullable = true, length = 20)
	@Pattern(regexp = "[0-9*#+() -]*", message = "validator.invalid.phone.format")
	private String phone;
//
//	@Column(name = "address", nullable = true, length = 45)
//	@Size(min = 6, max = 45, message = "validator.invalid.address")
//	private String address;
	
	@Column(name = "address", nullable = true, length = 45)
//	@Size(min = 6, max = 45, message = "validator.invalid.address")
	private String address;

	@Column(name = "avatar", nullable = true, length = 50)
	private String avatar;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = true)
	private Date dateUpdated;
	
	@OneToMany (mappedBy = "profile", fetch = FetchType.EAGER)
	private List<Skill> skills;
	
	/**
	 * Default Constructor
	 */
	public Profile() {
	}

	public Profile(long userID) {
		this.userID = userID;
	}

	/**
	 * Get userID
	 * 
	 * @return
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * Set userID
	 * 
	 * @param userID
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	/**
	 * Get user
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * set user
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * get firstname
	 * 
	 * @return firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Set value for firstname
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * get lastname
	 * 
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * set value for lastname
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * get email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set value for email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * get age
	 * 
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * set value for age
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * get gender
	 * 
	 * @return
	 */
	public byte getGender() {
		return gender;
	}

	/**
	 * set value for gender
	 * 
	 * @param gender
	 */
	public void setGender(byte gender) {
		this.gender = gender;
	}

	/**
	 * get phone
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * set value for phone
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * get adress
	 * 
	 * @return adress
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * set value for address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * get avatar
	 * 
	 * @return avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * set value for avatar
	 * 
	 * @param avatar
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	
}
