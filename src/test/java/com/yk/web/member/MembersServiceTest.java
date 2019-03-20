package com.yk.web.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MembersServiceTest {

	@Autowired
	private MembersService membersService;

	@Autowired
	private MembersRepository membersRepository;
	
	@After
	public void cleanUp() {
		membersRepository.deleteAll();
	}

	@Test
	public void testServiceJoin() {
		//given
		MemberRequestDto dto = MemberRequestDto.builder()
				.email("email@naver.com").nickname("nick").password("1234").build();

		//when
		membersService.createUserAccount(dto);
		
		//then
		Members member = membersRepository.findAll().get(0);
		assertThat(member.getEmail()).isEqualTo(dto.getEmail());
		assertThat(member.getNickname()).isEqualTo(dto.getNickname());
		assertThat(member.getPassword()).isEqualTo(dto.getPassword());
		
	}
	
	
	
	

}
