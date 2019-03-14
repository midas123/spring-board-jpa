package com.yk.web.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long>{
	
	public Members findByEmailAndPassword(String email, String pasword);
	
	
	
}