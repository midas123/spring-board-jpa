package com.yk.controller;


import java.security.Principal;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.yk.password.PasswordResetRequestDto;
import com.yk.password.PasswordResetTokenRepository;
import com.yk.user.dto.UserRequestDto;
import com.yk.user.entity.EmailToken;
import com.yk.user.entity.PasswordResetToken;
import com.yk.user.entity.Users;
import com.yk.user.service.EmailSendService;
import com.yk.user.service.UserServiceImpl;

import lombok.AllArgsConstructor;
@Controller
@AllArgsConstructor
public class WebController {
	
	  @Autowired 
	  private UserServiceImpl userServiceImpl;
	  
	  
	  @GetMapping("/")
	  public String main() {
	        return "main";
	    }
	  
	  @GetMapping("/registration")
	  public String registrationForm() {
	        return "registration";
	    }
	  
	  @GetMapping("/emailConfirmation/done")
	  public String confirmDone(Model model) {
		  return "confirmDone";
	  }
	  
	  @GetMapping("/board")
	  public String board(Principal principal) {
		  return "board";
	  }
	  
	  @GetMapping("/login")
	  public String loginError(Model model, @RequestParam(value = "error", required = false) String error ) {
		  if (error != null) {
			  model.addAttribute("loginError", true);
		  }
		  return "main";
	  }
	  
	  @GetMapping("/emailConfirmation/account")
	  public String confirmUserAccount(@RequestParam("token")String emailToken) {
		  userServiceImpl.confirmEmailToken(emailToken);
		  return "confirmDone";
	  }
	  
	
}
