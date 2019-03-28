package com.yk.web.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.yk.web.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="Users")
//@IdClass(UserPK.class)
public class Users extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;

    @Column(length = 45, nullable = false)
    private String username;

    @Column(length = 45, nullable = false)
    private String nickname;

    @Column(length = 60, nullable = false)
    private String password;
    
    @Column(length = 30, nullable = false)
    private String phonenumber;
    
    public Users() {
    	
    }
    
    public Users(Users user){
    }
    
    @Builder
    public Users(String username, String nickname, String password, String phoneNumber) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.phonenumber = phoneNumber;
    }

	/*public void setPassword(String password) {
		this.password = password;
	}*/
    
    
}
