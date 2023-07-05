package edu.pnu.config.auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 시큐리티 인가 필터
public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	// 사용자 정보를 얻기 위해 인터페이스 추가
	private MemberRepository memberRepo;
	
	// 생성자 부모 클래스가 authenticationManager인 객체 요구, MemberRepository를 요구
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepo) {
		super(authenticationManager);
		this.memberRepo = memberRepo;
	}
	
	@Override //필터링 메소드 오버라이드
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String srcToken = request.getHeader("Authorization");
		if (srcToken == null || !srcToken.startsWith("Bearer ")) { // 토큰 없거나, JWT토큰이 아니면
			chain.doFilter(request, response);
			return;
		}
		String jwtToken = srcToken.replace("Bearer ", "");
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwtkey")).build().verify(jwtToken).getClaim("username").asString();
		// DB에 검색해서 아이디찾음
		Optional<Member> opt = memberRepo.findById(username);
		if(!opt.isPresent()) { // 아이디 없으면 
			chain.doFilter(request, response);
			return;
		}
		
		Member findMember = opt.get();
		// DB에서 읽은 사용자 정보를 이용해서 UserDetails 타입의 객체를 만들어서
		User user = new User(findMember.getUsername(), findMember.getPassword(), findMember.getAuthorities());
		// Authentication 객체를 생성 : 사용자명과 권한 관리를 위한 정보를 입력(암호는 필요없음)
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		// 시큐리티 세션에 등록한다.
		SecurityContextHolder.getContext().setAuthentication(auth); 
		
		// 다음 필터로 이동
		chain.doFilter(request, response);
	}
	
}
