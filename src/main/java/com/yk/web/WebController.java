package com.yk.web;


import java.security.Principal;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.yk.web.dao.UserRequestDto;
import com.yk.web.entity.EmailToken;
import com.yk.web.entity.Users;
import com.yk.web.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {
	
	  UserService userservice;
	  
	  @GetMapping("/")
	  public String main() {
	        return "main";
	    }
	  
	  @GetMapping("/join")
	  public String joinForm() {
	        return "join";
	    }
	  
	  @GetMapping("/emailConfirm/done")
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
	  
	  @GetMapping("/emailConfirm/account")
	  public String confirmUserAccount(@RequestParam("token")String emailToken) {
		  userservice.confirmEmailToken(emailToken);
		  return "confirmDone";
	  }
	
	  
	  
	 
}
