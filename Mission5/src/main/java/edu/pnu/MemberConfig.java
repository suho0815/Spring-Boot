package edu.pnu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.pnu.dao.log.LogDao;
import edu.pnu.service.MemberService;

//@Configuration
public class MemberConfig { 
	// config파일(소스가 없을 때 사용) -> 
	
	//@Bean
	public MemberService memberService() {
		return new MemberService();
	}
	
	
}
