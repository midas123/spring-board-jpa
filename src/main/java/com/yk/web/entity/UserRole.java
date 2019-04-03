package com.yk.web.entity;


import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Userrole")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)    
	@Column(name="user_role_id")
	private int user_role_id;
	
	@Column(name="userid")
	private int userid;
	
	@Column(name="role")
	private String role; 
	
	public UserRole() {
		
	}
	
	@Builder
    public UserRole(int user_role_id, int userid, String role) {
        this.user_role_id = user_role_id;
        this.userid = userid;
        this.role = role;
    }
	
	public String getRole() {
	  return role;
	}
	
	public void setRole(String role) {
	  this.role = role;
	}
	
	public int getUserid() {
	  return userid;
	}
	
	public void setUserid(int userid) {
	  this.userid = userid;
	}
	
	public int getUserroleid() {
	  return user_role_id;
	}
	
	public void setUserroleid(int userroleid) {
	  this.user_role_id = userroleid;
	} 
}