package com.yk.web.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yk.web.image.UserImageService;
import com.yk.web.image.UserImages;
import com.yk.web.image.UserImagesRequestDto;
import com.yk.web.user.dto.UserRequestDto;
import com.yk.web.user.entity.Users;
import com.yk.web.user.service.UserServiceImpl;

import lombok.AllArgsConstructor;
@Controller
@AllArgsConstructor
public class WebUserController {
	
	  @Autowired 
	  private UserServiceImpl userServiceImpl;
	  
	  @Autowired
	  private UserImageService userImageService;
	  
	  @GetMapping("/")
	  public String main() {
	        return "main";
	    }
	  
	  @GetMapping("/registration")
	  public String registrationForm() {
	        return "registration";
	    }
	  
	  
	/*  @PostMapping("/registration")
	    public String userRegistration(@Valid UserRequestDto userDto) throws IOException {
	    	long userId =  userServiceImpl.userResistrationPro(userDto);
	    	UserImagesRequestDto dto = userDto.toConvertUserImagesRequestDto();
	    	dto.setUsers_images(new Users(userId));
	    	
	    	MultipartFile file = dto.getFile_data();
			String fileOriginName = file.getOriginalFilename();
			String fileType = file.getContentType();
			dto.setFile_type(fileType);
			dto.setFile_origin_name(fileOriginName);
	    	UUID uuid = UUID.randomUUID();
	    	dto.setFile_save_name(uuid.toString()+"_"+fileOriginName);
	    	
	    	userImageService.saveFile(dto);
	    	
	    	return "main";
	    }*/
	  
	  @GetMapping("/board")
	  public String board(Principal principal, Model model) {
		  UserImages userImages = userImageService.serveUserImage(principal.getName());
		  String saved_filename = userImages.getFile_save_name();
		  
		  model.addAttribute("filename", saved_filename);
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
		  userServiceImpl.confirmEmailToken(emailToken);
		  return "confirmDone";
	  }
	  
	
}
