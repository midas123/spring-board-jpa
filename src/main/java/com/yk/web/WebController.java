package com.yk.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
	  public String board() {
		  return "board";
	  }
}
