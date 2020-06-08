package com.brycen.hrm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name = "rolePermission")
public class RolePermission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rolePermissionId")
	private Long rolePermissionId;
	
	@Column(name = "roleId", updatable = false , insertable = false)
	private long roleID;

	@Column(name = "permissionId", updatable = false, insertable = false)
	private long permissionId;
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinColumn (name = "roleId", referencedColumnName = "roleId")
	private List<Role> roles;
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private List<User> users;

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
