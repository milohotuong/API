package com.brycen.hrm.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
import javax.persistence.Table;

@Entity
@Table(name = "userRole")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userRoleId")
	private long userRoleId;
	
	@Column(name = "userId", updatable = false, insertable = false)
	private long userId;

	@Column(name = "roleId", updatable = false, insertable = false)
	private long roleId;
	
	@OneToOne(mappedBy = "userRole", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Role role;

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
//	@ManyToMany (fetch = FetchType.LAZY)
//	@JoinColumn (name = "roleId", referencedColumnName = "roleId")
//	private Set<Role> roles;
//	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

//	public UserRoleKey getUserRoleKey() {
//		return userRoleKey;
//	}
//
//	public void setUserRoleKey(UserRoleKey userRoleKey) {
//		this.userRoleKey = userRoleKey;
//	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
//
//	public User getUsers() {
//		return user;
//	}
//
//	public void setUsers(User users) {
//		this.user = users;
//	}

	
	
}
