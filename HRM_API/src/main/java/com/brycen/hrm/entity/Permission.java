package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "permissionId")
	private Long permissionId;
	
	
}
