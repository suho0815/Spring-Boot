package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Component // @Component : IOC컨테이너에 해당 클래스를 등록한다. 그래야 실행이 가능하다.
public class MemberInitialize implements ApplicationRunner {

	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("a"))	//encode() : password를 알아보지 못하게 바꿔준다.
				.role("ROLE_MEMBER")			//ROLE_ : DB에서 규칙이다. 생략 불가능. ??정확히는 무슨 말인지 이해하지 못했다.
				.enabled(true).build()); 
		
		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("a"))
				.role("ROLE_MANAGER")
				.enabled(true).build());
		
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("a"))
				.role("ROLE_ADMIN")
				.enabled(true).build());
	}

}
