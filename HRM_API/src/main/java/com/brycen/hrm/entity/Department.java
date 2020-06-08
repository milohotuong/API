//package com.brycen.hrm.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//@Entity
//@Table(name = "department")
//public class Department {
//	public static final Long ROOT_ID = 0L;
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    @Column(name="departmentId")
//    private Long departmentId;
//
//    @Column(name="PARENT_ID")
//    private Long parentId;
//
//    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "PARENT_ID", nullable = false, insertable = false, updatable = false)
//    private Department parent;
//
//    @Column(name="LABEL")
//    private String label;
//
//    @OneToMany(mappedBy = "parent", 
//               targetEntity = Department.class, 
//               fetch=FetchType.LAZY, 
//               cascade = {CascadeType.PERSIST, CascadeType.ALL})
//    private List<Department> children;
//    
//    @OneToMany(mappedBy = "department")
//    private List<User> users;
//}
