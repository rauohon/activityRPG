package com.activityRPG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.Email;
   
	@Service
    public class EmailSender  {
         
		@Autowired
		   private JavaMailSenderImpl javaMailSenderImpl;
		   
		   ModelAndView mav=null;
		   
		   
		   public ModelAndView entrance(int serviceCode, Email b) {
		      
		      switch(serviceCode) {
		      
		      case 0:
		         mav = mailSender();
		         break;
		      }
		      
		      return mav;
		   }
		   
		   
		   public ModelAndView mailSender() {
		      mav=new ModelAndView();
		      
		      // 일반 텍스트 이메일
		      SimpleMailMessage smm = new SimpleMailMessage();
		      smm.setFrom("rnjdejr@gmail.com");
		      smm.setTo("rnjdejr@naver.com");
		      smm.setSubject("simple 테스트 메일");
		      smm.setText("메일 내용");
		      
		      javaMailSenderImpl.send(smm);
		      
		      mav.setViewName("memberInfo");
		      
		      return mav;
		   }

}


