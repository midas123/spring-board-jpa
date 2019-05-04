package com.yk.web.springsecurity;


import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.yk.web.user.entity.Users;


public class CustomUserDetails extends Users implements UserDetails { 
	
	private static final long serialVersionUID = 1L;
	private List<String> userRoles;

	private boolean accountNonExpired = true; 
	private boolean accountNonLocked = true; 
	private boolean credentialsNonExpired = true; 
	private boolean enabled = true;
	
 
	public CustomUserDetails(Users user, List<String> userRoles){
	    super(user);
	    this.userRoles=userRoles;
	 }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    String roles=StringUtils.collectionToCommaDelimitedString(userRoles);   
	    return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	
	 @Override 
	 public boolean isAccountNonExpired() { 
		 return this.accountNonExpired; 
		 } 
	 
	 public void setAccountNonExpired(boolean accountNonExpired) { 
		 this.accountNonExpired = accountNonExpired; 
		 } 
	 
	 @Override 
	 public boolean isAccountNonLocked() { 
		 return this.accountNonLocked; 
		 
		 } 
	 
	 public void setAccountNonLocked(boolean accountNonLocked) { 
		 this.accountNonLocked = accountNonLocked; 
		 }
	 
	 @Override 
	 public boolean isCredentialsNonExpired() { 
		 return this.credentialsNonExpired; 
		 } 
	 
	 public void setCredentialsNonExpired(boolean credentialsNonExpired) { 
		 this.credentialsNonExpired = credentialsNonExpired; 
		 } 
	 
	 @Override 
	 public boolean isEnabled() { 
		 return this.enabled; 
	} 
	 
	 public void setEnabled(boolean enabled) { 
		 this.enabled = enabled; 
	}
	
	
	@Override
	public String getUsername() {
		return super.getUsername();
	}

}