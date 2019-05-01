package com.yk.web.service;

import org.springframework.stereotype.Service;
import com.yk.web.dao.PostRepository;
import com.yk.web.dto.PostRequestDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService {
	private PostRepository boardsRepository;
	
	public void writePost(PostRequestDto dto) {
		boardsRepository.save(dto.toEntity());
	}
	
}
