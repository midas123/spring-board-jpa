package com.yk.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MembersRepositoryTest {
  	@Autowired
    MembersRepository membersRepository;

    @After
    public void cleanup() {
    	membersRepository.deleteAll();
    }
	    
	    
	@Test
	public void joinMembership() {
		//given
		LocalDateTime now = LocalDateTime.now();
		membersRepository.save(Members.builder()
				.email("email@naver.com")
				.nickname("닉네임")
				.password("password").build());
		
		//when
		List<Members> memberList = membersRepository.findAll();
		
		//then
		Members member = memberList.get(0);
		assertThat(member.getNickname(), is("닉네임"));
		assertThat(member.getPassword(), is("password"));
		
		assertTrue(member.getCreatedDate().isAfter(now));
        assertTrue(member.getModifiedDate().isAfter(now));
	}

}
