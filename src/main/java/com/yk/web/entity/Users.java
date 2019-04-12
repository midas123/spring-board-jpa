package com.yk.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="Users")
public class Users extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(length = 45, nullable = false)
    private String username;

    @Column(length = 45, nullable = false)
    private String nickname;

    @Column(length = 60, nullable = false)
    private String password;
    
    @Column(length = 30, nullable = false)
    private String phonenumber;
    
    @Column(nullable=true)
    private boolean isEnabled;
    
    
    public Users(String username, int userid) {
    	 this.username = username;
    	 this.userid = userid;
    }
    
    public Users(Users user){
        this.userid = user.userid;
        this.username = user.username;
        this.password = user.password;
    }
    
    @Builder
    public Users(String username, String nickname, String password, String phoneNumber) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.phonenumber = phoneNumber;
    }

	public void setIsenabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
    
	public boolean getIsenabled() {
		return isEnabled;
	}
    
}
