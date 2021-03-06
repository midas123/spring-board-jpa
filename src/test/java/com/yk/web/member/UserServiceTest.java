package com.yk.web.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yk.web.user.dao.UserRepository;
import com.yk.web.user.dto.UserRequestDto;
import com.yk.web.user.entity.Users;
import com.yk.web.user.service.UserServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserServiceImpl membersService;

	@Autowired
	private UserRepository membersRepository;
	
	@After
	public void cleanUp() {
		membersRepository.deleteAll();
	}

	@Test
	public void testServiceJoin() {
		//given
		UserRequestDto dto = UserRequestDto.builder()
				.email("email@naver.com").nickname("nick").password("1234").build();

		//when
		membersService.userResistrationPro(dto);
		
		//then
		Users member = membersRepository.findAll().get(0);
		assertThat(member.getUsername()).isEqualTo(dto.getUsername());
		assertThat(member.getNickname()).isEqualTo(dto.getNickname());
		assertThat(member.getPassword()).isEqualTo(dto.getPassword());
		
	}
	
	
	
	

}
