package com.yk.web.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yk.web.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	public Users findByUsernameAndPassword(String email, String password);
	
	public Users findByUsername(String email);
	
	public Users findByUsernameIgnoreCase(String email);
	
	Optional<Users> findByNickname(String nickname);
	
	
}