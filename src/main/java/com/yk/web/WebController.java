package com.yk.web;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.yk.web.user.UserRequestDto;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {
		
	
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
}
