package com.yk.web.springsecurity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.yk.web.user.dao.UserRepository;
import com.yk.web.user.dao.UserRolesRepository;
import com.yk.web.user.entity.Users;
import com.yk.web.user.valid.ValidCustomException;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository,UserRolesRepository userRolesRepository) {
	    this.userRepository = userRepository;
	    this.userRolesRepository=userRolesRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=userRepository.findByUsername(username);
		if(user != null && user.getIsenabled() == false || user == null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
		} else{
			List<String> userRoles=userRolesRepository.findRoleByUsername(username);
			return new CustomUserDetails(user,userRoles);
		}
	}
}