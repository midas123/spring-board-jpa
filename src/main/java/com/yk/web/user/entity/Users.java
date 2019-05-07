package com.yk.web.user.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yk.web.post.entity.PostLikes;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="Users")
@ToString
public class Users extends BaseTimeEntity implements Serializable {
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
    
    @JsonManagedReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PostLikes> postLikes;
    
    public Users(String nickname, int userid){
    	this.nickname =nickname;
    	this.userid = userid;
    }
    
    /*public Users(String username, int userid) {
    	 this.username = username;
    	 this.userid = userid;
    }*/
    
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
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
}
