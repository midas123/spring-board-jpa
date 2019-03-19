package com.yk.web.member;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
		String field();
		
		String fieldMatch();
		
	  	String message() default "비밀번호가 서로 다릅니다.";
	  	
	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};


	    @Target({ ElementType.TYPE })
	    @Retention(RetentionPolicy.RUNTIME)
	    @interface List {
	    	PasswordMatch[] value();
	    }
	    
}
