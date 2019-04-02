package com.yk.web;


import java.security.Principal;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.yk.web.entity.EmailToken;
import com.yk.web.user.UserRequestDto;
import com.yk.web.user.UserService;
import com.yk.web.user.Users;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {
	  UserService userservice;
	
/*	  @ModelAttribute("MemberDto")
	    public MemberRequestDto getMemberDto() {
	        return new MemberRequestDto();
	   }*/
	  
	  @GetMapping("/")
	  public String main() {
	        return "main";
	    }
	  
	  @GetMapping("/join")
	  public String joinForm() {
	        return "join";
	    }
	  
	  /*@GetMapping("/emailConfirm/page")
	  public String confirmPage(Model model) {
		  return "confirmPage";
	  }*/
	  
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
