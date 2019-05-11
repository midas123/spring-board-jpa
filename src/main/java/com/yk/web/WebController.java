package com.yk.web;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		  System.out.println("registration");
	        return "registration";
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
	  
	  @GetMapping("/emailConfirmation/done")
	  public String confirmDone(Model model) {
		  return "confirmDone";
	  }
	  
	  @GetMapping("/emailConfirmation/account") 
	  public String confirmUserAccount(@RequestParam("token")String emailToken) {
		  System.out.println("11:"+emailToken);
		  userServiceImpl.confirmEmailToken(emailToken);
		  return "confirmDone";
	  }
	  
	
}
