package com.brycen.hrm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "roleId", unique = true, nullable = true)
	private long roleId;
	
	@Column(name = "roleName", unique = true, nullable = true)
	private String roleName;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserRole userRole;

//	@ManyToMany
//	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
//	private List<UserRole> userRoles;
//	
//	@ManyToMany (fetch = FetchType.LAZY)
//	@JoinColumn (name = "roleId", referencedColumnName = "roleId")
//	private List<Role> roles;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

//	public List<UserRole> getUserRoles() {
//		return userRoles;
//	}
//
//	public void setUserRoles(List<UserRole> userRoles) {
//		this.userRoles = userRoles;
//	}
//
//	public List<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}
	
	
}
