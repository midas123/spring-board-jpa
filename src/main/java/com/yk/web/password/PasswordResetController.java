package com.yk.web.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yk.web.dao.UserRepository;
import com.yk.web.entity.Users;
import com.yk.web.service.EmailSendService;
import com.yk.web.service.UserServiceImpl;

import java.util.UUID;

import javax.validation.Valid;

@Controller
public class PasswordResetController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordResetTokenRepository tokenRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private EmailSendService emailSendService;
    

    @GetMapping("/ResetPassword")
    public String PasswordResetPage(@ModelAttribute("pfdto") PasswordResetRequestDto pfdto, String mailSended, Model model) {
	  	if(mailSended != null) {
	  		model.addAttribute("mailSended", true);
	  	}
	  
        return "passwordResetForm";
    }

    
    @PostMapping("/ResetPassword/verificationMail")
    public String PasswordResetPro(@ModelAttribute("pfdto") @Valid PasswordResetRequestDto pfdto,
                                            BindingResult result, Errors errors) {
        if (result.hasErrors()){
            return "passwordResetForm";
        }

        Users user = userRepository.findByUsername(pfdto.getEmail());
        if (user == null){
            result.rejectValue("email", null, "이메일 주소가 존재하지 않습니다.");
            return "passwordResetForm";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("비밀번호 변경 이메일 입니다.");
        mailMessage.setFrom("admin@gmail.com");
        mailMessage.setText("링크를 클릭해서 비밀번호를 변경합니다..: "
        +"http://localhost:8080/ResetPassword/valid?token="+token.getToken());
        emailSendService.sendEmail(mailMessage);

        return "redirect:/ResetPassword/?mailSended";

    }
    
    
    @GetMapping("/ResetPassword/valid")
    public String PasswordChangePage(@ModelAttribute("prdto") PasswordResetDto prdto, 
    		@RequestParam(required = false) String token, Model model) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null){
            model.addAttribute("error", "유효한 token이 아닙니다.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "token의 유효기간이 종료되었습니다.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        return "passwordResetToken";
    }

    
    @PostMapping("/ResetPassword/pro")
    @Transactional
    public String PasswordChangePagePro(Model model, @ModelAttribute("prdto") @Valid PasswordResetDto prdto,  
                                      BindingResult result, Errors errors) {
        if (result.hasErrors()){
        	model.addAttribute("token", prdto.getToken());
        	model.addAttribute("error", "유효한 token이 아니므로 비밀번호를 변경 할 수 없습니다.");
        	return "passwordResetToken";
        }
        PasswordResetToken token = tokenRepository.findByToken(prdto.getToken());
        if(token == null || token.isExpired())
        	return "403";
        Users user = token.getUser();
        String updatedPassword = passwordEncoder.encode(prdto.getPassword());
        userRepository.updatePassword(updatedPassword, user.getUserid());
        tokenRepository.delete(token);

        return "redirect:/ResetPassword/done";
    }
    
    @GetMapping("/ResetPassword/done")
    public String passwordCheangeDone() {
        return "passwordResetDone";
    }

}