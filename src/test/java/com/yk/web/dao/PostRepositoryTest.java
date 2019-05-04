package com.yk.web.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yk.web.post.dao.PostRepository;
import com.yk.web.post.entity.Posts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
	@Autowired
	PostRepository postRepository;
	
	@After
	public void cleanup() {
		//postRepository.deleteAll();
	}
	
	@Test
	public void test() {
		//given
		postRepository.save(Posts.builder()
				.nickname("닉")
				.p_title("제목")
				.p_content("내용")
				.p_counts(0)
				.build());
		//when
		List<Posts> list =postRepository.findAll();
		
		//then
		Posts post = list.get(0);
		assertThat(post.getP_title(), is("제목"));
		assertThat(post.getP_content(), is("내용"));
	}

}
