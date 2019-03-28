package com.yk.web.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yk.web.exception.ValidCustomException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
	private UserRepository userRepository;
	private UserRolesRepository userRolesRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
    public Long createUserAccount(UserRequestDto dto){
		verifyDuplicateEmail(dto.getUsername());
		verifyDuplicateNickName(dto.getNickname());
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return userRepository.save(dto.toEntity()).getUserid();
	}
	
	private void verifyDuplicateEmail(String email){
	    if(userRepository.findByUsername(email) != null){
	        throw new ValidCustomException("이미 사용중인 이메일 주소 입니다", "email");
	    }
	}
	
	private void verifyDuplicateNickName(String nickname) {
		 if(userRepository.findByNickname(nickname).isPresent()){
		        throw new ValidCustomException("이미 사용중인 닉네임 입니다", "nickname");
		    }
	}
	
	private void registerUser(UserRequestDto dto) {
		userRepository.save(dto.toEntity());
	}
	
	/*public User userLogin(MemberRequestDto dto) {
		return membersRepository.findByUsernameAndPassword(dto.getEmail(), dto.getPassword());
	}*/
	
	
}
