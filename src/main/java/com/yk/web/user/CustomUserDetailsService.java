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
		System.out.println("loadUserByUsername:"+username);
		Users user=userRepository.findByUsername(username);
		if(null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		} else {
			System.out.println("password:"+user.getPassword());
			List<String> userRoles=userRolesRepository.findRoleByUsername(username);
			System.out.println("size:"+userRoles.size());
			return new CustomUserDetails(user,userRoles);
		}
	}
}