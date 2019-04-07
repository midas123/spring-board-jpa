package com.yk.web.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yk.web.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	public Users findByUsernameAndPassword(String email, String password);
	
	public Users findByUsername(String email);
	
	public Users findByUsernameIgnoreCase(String email);
	
	Optional<Users> findByNickname(String nickname);

    @Modifying
    @Query("update Users u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") int id);
}