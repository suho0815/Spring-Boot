package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import lombok.Builder;

@SpringBootTest
public class MemberInitialize {

	@Autowired
	MemberRepository memberRepo;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder(); // 비밀번호 암호화 인터페이스/구현체
	
	@Test
	public void doTest() {
		memberRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("abcd"))
				.role("ROLE_MEMBER")
				.enable(true).build());
		
		memberRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("abcd"))
				.role("ROLE_MANAGER")
				.enable(true).build());
		
		memberRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.role("ROLE_ADMIN")
				.enable(true).build());
		
	}
	
}
