package com.yk.web.member;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidataionTest {

	@Test
	public void test() {
		 //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         
        //It validates bean instances
        Validator validator = factory.getValidator();
        

        MemberRequestDto user = new MemberRequestDto("abcgmail.com", "nick", "1234", "010111222222");
 
        //Validate bean
        Set<ConstraintViolation<MemberRequestDto>> constraintViolations = validator.validate(user);
        

        //Show errors
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<MemberRequestDto> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("Valid Object");
        }
	}

}
