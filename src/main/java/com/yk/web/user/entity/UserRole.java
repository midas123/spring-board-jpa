package com.yk.web.user.entity;


import javax.persistence.*;
import lombok.Builder;

@Entity
@Table(name="Userrole")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)    
	@Column(name="user_role_id")
	private long user_role_id;
	
/*	@Column(name="userid")
	private int userid;*/
	
	@Column(name="role")
	private String role; 
	
	@OneToOne
	@JoinColumn(name="userid")
	private Users users;
	
	public UserRole() {
		
	}
	
	@Builder
    public UserRole(long user_role_id, Users users, String role) {
        this.user_role_id = user_role_id;
        this.users = users;
        this.role = role;
    }
	
	public String getRole() {
	  return role;
	}
	
	public void setRole(String role) {
	  this.role = role;
	}
	
	public long getUserroleid() {
	  return user_role_id;
	}
	
	public void setUserroleid(long userroleid) {
	  this.user_role_id = userroleid;
	}

	public long getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(long user_role_id) {
		this.user_role_id = user_role_id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	} 
}