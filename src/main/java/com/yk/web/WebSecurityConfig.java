package com.yk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yk.web.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
	  auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	 } 
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		//h2-console
		http.authorizeRequests().antMatchers("/h2-console/*").permitAll();
		http.headers().frameOptions().disable();
		//h2-console END
		
		http.authorizeRequests()
        .antMatchers("/", "/css/**", "/js/**", "/img/**").permitAll()
        .antMatchers("/join/**").hasRole("ANONYMOUS").anyRequest().authenticated()
        .antMatchers("/board").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        //.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
		.formLogin()
        .loginPage("/")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/board", true)
        .usernameParameter("username")
        .passwordParameter("password")
        .and()
        .logout()
        .and()
        .exceptionHandling().accessDeniedPage("/403");
	}

	/* @Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(authenticationProvider);
	    }
	*/
	
	
	@Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }
	
	
}
