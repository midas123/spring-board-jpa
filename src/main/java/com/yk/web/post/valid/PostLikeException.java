package com.yk.web.post.valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PostLikeException extends RuntimeException{
	public PostLikeException(String message) {
		super(message);
	}

}
