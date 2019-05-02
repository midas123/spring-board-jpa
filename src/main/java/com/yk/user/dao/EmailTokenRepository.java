package com.yk.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.yk.user.entity.EmailToken;

public interface EmailTokenRepository extends CrudRepository<EmailToken, String> {  
	EmailToken findByConfirmationToken(String confirmationToken);
}