package com.yk.web.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.yk.web.BaseTimeEntity;
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
    
}
