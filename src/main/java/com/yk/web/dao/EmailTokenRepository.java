package com.yk.web.dao;

import org.springframework.data.repository.CrudRepository;
import com.yk.web.entity.EmailToken;

public interface EmailTokenRepository extends CrudRepository<EmailToken, String> {  
	EmailToken findByConfirmationToken(String confirmationToken);
}