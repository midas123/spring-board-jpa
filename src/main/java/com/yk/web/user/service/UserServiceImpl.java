package com.yk.web.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yk.web.user.dao.EmailTokenRepository;
import com.yk.web.user.dao.UserRepository;
import com.yk.web.user.dao.UserRolesRepository;
import com.yk.web.user.dto.UserRequestDto;
import com.yk.web.user.entity.EmailToken;
import com.yk.web.user.entity.UserRole;
import com.yk.web.user.entity.Users;
import com.yk.web.user.valid.ValidCustomException;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Service
public class UserServiceImpl {
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @Autowired
    private EmailSendService emailSenderService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
    public long userResistrationPro(UserRequestDto dto){
		verifyDuplicationEmail(dto.getUsername());
		verifyDuplicationNickName(dto.getNickname());
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		long userId = userRepository.save(dto.toEntity()).getUserid();
		UserRoleResistration(userId);
		sendVerifycationEmail(dto, userId);
		return userId;
	}
	
	private void verifyDuplicationEmail(String email){
	    if(userRepository.findByUsername(email) != null){
	        throw new ValidCustomException("이미 사용중인 이메일 주소 입니다", "username");
	    }
	}
	
	private void verifyDuplicationNickName(String nickname) {
		 if(userRepository.findByNickname(nickname).isPresent()){
		        throw new ValidCustomException("이미 사용중인 닉네임 입니다", "nickname");
		    }
	}
	
	private void UserRoleResistration(long userid) {
		UserRole userRole = new UserRole();
		userRole.setUsers(new Users(userid));
		userRole.setRole("ROLE_USER");
		userRolesRepository.save(userRole);
	}
	
	private void sendVerifycationEmail(UserRequestDto dto, long userid) {
		  Users user = new Users(dto.getUsername(), userid);
		  EmailToken emailToken = new EmailToken(user);

		  emailTokenRepository.save(emailToken);

          SimpleMailMessage mailMessage = new SimpleMailMessage();
          mailMessage.setTo(dto.getUsername());
          mailMessage.setSubject("회원 가입을 환영합니다.");
          mailMessage.setFrom("admin@gmail.com");
          mailMessage.setText("링크를 클릭해서 귀하의 이메일을 인증합니다.: "
          +"http://localhost:8080/emailConfirmation/account?token="+emailToken.getConfirmationToken());
          emailSenderService.sendEmail(mailMessage);
		
	}
	
	public void confirmEmailToken(String token) {
		System.out.println("token");
		EmailToken emailtoken = emailTokenRepository.findByConfirmationToken(token);
        if(emailtoken != null) {
            Users user = userRepository.findByUsernameIgnoreCase(emailtoken.getUser().getUsername());
            user.setIsenabled(true);
            userRepository.save(user);
            emailTokenRepository.delete(emailtoken);
        } else {
        	throw new ValidCustomException("인증 token 또는 주소가 적절하지 않습니다.", "confirm-email-errormessage");
        }
	}
	
	/*public void IsUserEmailTokenEnabled(UserRequestDto dto) {
		Users user = userRepository.findByUsername(dto.getUsername());
		if(!user.getIsenabled()) {
			throw new ValidCustomException("먼저 회원님 이메일 인증을 진행해주세요.", "Not-Enabled");
		}
	}*/
	
}
