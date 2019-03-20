package com.yk.web.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.yk.web.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Members extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String nickname;

    @Column(length = 60, nullable = false)
    private String password;
    
    @Column(length = 30, nullable = false)
    private String phoneNumber;

    @Builder
    public Members(String email, String nickname, String password, String phoneNumber) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
