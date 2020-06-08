package com.brycen.hrm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "skill")
public class Skill {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "skillId", unique = true, nullable = true)
	private long skillId;
	
	@Column(name = "profileId", unique = true, nullable = true, insertable = false, updatable = false)
	private long profileId;
	
	@Column(name = "skillName", unique = false, nullable = true)
	private String skillName;
	
	@Column(name = "skillDetail", unique = true, nullable = true)
	private String skillDetail;
	
	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillDetail() {
		return skillDetail;
	}

	public void setSkillDetail(String skillDetail) {
		this.skillDetail = skillDetail;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "profileId", referencedColumnName = "user_id")
	private Profile profile;
	
//	@ManyToMany (fetch = FetchType.LAZY)
//	@JoinColumn (name = "roleId", referencedColumnName = "roleId")
//	private List<Role> roles;



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
