package com.yk.web.member;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MembersService {
	private MembersRepository membersRepository;
	
	@Transactional
    public Long join(MemberJoinRequestDto dto){
	      return membersRepository.save(dto.toEntity()).getNum();
	}
	
}
