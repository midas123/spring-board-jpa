package com.yk.web.member;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yk.web.exception.ValidCustomException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MembersService {
	private MembersRepository membersRepository;
	
	@Transactional
    public Long createUserAccount(MemberRequestDto dto){
		verifyDuplicateEmail(dto.getEmail());
		verifyDuplicateNickName(dto.getNickname());
		return membersRepository.save(dto.toEntity()).getNum();
	}
	
	private void verifyDuplicateEmail(String email){
	    if(membersRepository.findByEmail(email).isPresent()){
	        throw new ValidCustomException("이미 사용중인 이메일 주소 입니다", "email");
	    }
	}
	
	private void verifyDuplicateNickName(String nickname) {
		 if(membersRepository.findByNickname(nickname).isPresent()){
		        throw new ValidCustomException("이미 사용중인 닉네임 입니다", "nickname");
		    }
	}
	
	public Members userLogin(MemberRequestDto dto) {
		return membersRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
	}
	
	
}
