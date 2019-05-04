package com.yk.web;


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

import com.yk.web.password.PasswordResetRequestDto;
import com.yk.web.password.PasswordResetTokenRepository;
import com.yk.web.user.dto.UserRequestDto;
import com.yk.web.user.entity.EmailToken;
import com.yk.web.user.entity.PasswordResetToken;
import com.yk.web.user.entity.Users;
import com.yk.web.user.service.EmailSendService;
import com.yk.web.user.service.UserServiceImpl;

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
