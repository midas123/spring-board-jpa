package com.yk.web.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yk.web.image.UserImageService;
import com.yk.web.image.UserImages;

@Controller
public class WebImageController {
	@Autowired
	UserImageService userImageService;
	
	@Autowired
    private ServletContext servletContext;
	
	@GetMapping("/image/user/{userid}")
	public String getImageFile(@PathVariable long userid){
		
		return "done";
	}
	
	@GetMapping("/image/download/user/{userid}")
	public ResponseEntity<byte[]> downImageFile(@PathVariable long userid){
		UserImages ui = userImageService.getOneUserImage(userid);
		byte[] file = ui.getFile_data();
		return new ResponseEntity<byte[]> (file, new HttpHeaders(), HttpStatus.OK);
	}
	
	
}