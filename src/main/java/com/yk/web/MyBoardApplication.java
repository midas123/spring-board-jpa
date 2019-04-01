package com.yk.web;

import java.util.Arrays;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.yk.web.exception.ValidCustomException;


@EnableJpaAuditing
@SpringBootApplication
public class MyBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBoardApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {
	        System.out.println("Let's inspect the beans provided by Spring Boot:");
	        String[] beanNames = ctx.getBeanDefinitionNames();
	        Arrays.sort(beanNames);
	        for (String beanName : beanNames) {
	            System.out.println(beanName);
	        }
	
	    };
	}
	
	@Bean
	public ErrorAttributes errorAttributes() {
		
	  return new DefaultErrorAttributes() {
	    @Override
	    public Map<String, Object> getErrorAttributes(WebRequest requestAttributes,
	        boolean includeStackTrace) {
	      Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
	      Throwable error = getError(requestAttributes);
	      if (error instanceof ValidCustomException) {
	        errorAttributes.put("errors", ((ValidCustomException)error).getErrors());
	      }
	      return errorAttributes;
	    }

	  };
	}
	
	@Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

	



}
