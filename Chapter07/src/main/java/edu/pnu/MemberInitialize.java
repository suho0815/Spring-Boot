package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Component
public class MemberInitialize implements ApplicationRunner {

	@Autowired
	MemberRepository memRepo;
	
	@Autowired //패스워드 인코딩 해주는거 갖고오기. 불가역적 인코딩.
	BCryptPasswordEncoder encoder; 
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("a")) //알수없는 문자로 인코딩. 역으로 디코딩 못한다.
				.role("ROLE_MEMBER")
				.enabled(true)
				.build()); //내부적으로 돌땐 ROLE_ 이걸 반드시 써줘야한다.
		
		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("a"))
				.role("ROLE_MANAGER")
				.enabled(true)
				.build());
		
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("a"))
				.role("ROLE_ADMIN")
				.enabled(true)
				.build());
	}

}