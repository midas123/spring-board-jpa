package com.yk.web.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.yk.web.user.entity.EmailToken;

public interface EmailTokenRepository extends CrudRepository<EmailToken, String> {  
	EmailToken findByConfirmationToken(String confirmationToken);
}