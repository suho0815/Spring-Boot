package edu.pnu.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public void save(Member member, HttpServletResponse response) throws IOException {
		Optional<Member> option = memberRepo.findById(member.getUsername());
		if(option.isPresent()) {
			response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 중복됨.');</script>");
            out.flush();
		}else {
			member.setPassword(encoder.encode(member.getPassword()));
			member.setRole("ROLE_MEMBER");
			member.setEnable(true);
			
			memberRepo.save(member);
		}
		
	}
	
}
