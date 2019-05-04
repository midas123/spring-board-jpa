package com.yk.web.springsecurity;

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
/*		http.authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
        .antMatchers("/login**", "/emailConfirmation/**", "/ResetPassword/**").permitAll()
        .antMatchers("/registration").hasRole("ANONYMOUS")
        .antMatchers("/board").hasRole("USER").anyRequest().authenticated()
        .and()
		.formLogin()
        .loginPage("/")
        .loginProcessingUrl("/loginPro")
        .defaultSuccessUrl("/board", true)
        .failureUrl("/login?error")
        .usernameParameter("username")
        .passwordParameter("password")
        .permitAll()
        .and()
        .logout()
        .and()
        .exceptionHandling().accessDeniedPage("/403");*/
	}
	
	@Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }
}
