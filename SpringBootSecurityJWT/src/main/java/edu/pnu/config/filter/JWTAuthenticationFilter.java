package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
// 시큐리티 인증 필터
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
//	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
	
	@Override
	// 로그인 인증 시도
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		// JSON -> object로 변경하기 위한 객체
		ObjectMapper om = new ObjectMapper();
		try {
			// request의 Body에 JSON으로 담겨오는 username/password를 읽어서 Member 객체로 받아온다.
			// req.getInputStream()에서 직접 읽어서 객체를 만들어도 된다. 자신감을 가지고 해보자.
			Member member = om.readValue(request.getInputStream(), Member.class);

			
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			//UserDetails – loadUserbyUsername 메소드에서 DB 검색 후 리턴되어 온 객체를 Session의 Authenticaton에 등록한다
			Authentication auth = authenticationManager.authenticate(authToken);

			// 읽어들인 member정보 콘솔에 출력
			log.info("attemptAuthentication :[" + member + "]");
			return auth;
		}catch(Exception e) {
			log.info("Not Authenticated : " + e.getMessage());
		}
		
		return null;
	}
	
	@Override
	// 로그인 완료 후 처리, 로그인 성공 시 자동으로 호출 됨.
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	//Authentication authResult는 유저 정보
		User user = (User)authResult.getPrincipal();
		log.info("successfulAuthentication:" + user.toString());
		//JWT 생성
		String jwtToken = JWT.create()
							.withClaim("username", user.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60*10)) // 토큰 유효 시간
							.sign(Algorithm.HMAC256("edu.pnu.jwtkey")); //암호화 알고리즘 HMAC256를 사용(키 입력)
		// 응답 Header에 ＂Authorization＂이라는 키를 추가해서 JWT를 설정
		// Bearer : JWT임을 나타내는 용어, Basic : ＂Basic ＂+Base64(username:password)
		response.addHeader("Authorization", "Bearer " + jwtToken); //응답할 페이지에 헤더 추가 key, value (Bearer 약속된 코드임)
		chain.doFilter(request, response);
		
	}
	
}
