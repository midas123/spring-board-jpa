package com.yk.web.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


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
		if(null == user){
			throw new UsernameNotFoundException("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
		} else {
			List<String> userRoles=userRolesRepository.findRoleByUsername(username);
			return new CustomUserDetails(user,userRoles);
		}
	}
}