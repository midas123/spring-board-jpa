package com.yk.web.member;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MembersService {
	private MembersRepository membersRepository;
	
	@Transactional
    public Long join(MemberRequestDto dto){
		return membersRepository.save(dto.toEntity()).getNum();
	}
	
	public Members login(MemberRequestDto dto) {
		return membersRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
	}
	
}
