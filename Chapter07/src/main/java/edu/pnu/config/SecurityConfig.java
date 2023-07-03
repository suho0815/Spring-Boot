package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // bean 객체 등록해야 함, 시큐리티에서 제공되는ㄴ걸 사용못하게 막는다>>?
@EnableWebSecurity // 시큐리티 설정파일임을 의미, 시큐리티를 사용하는 데 필요한 객체 생성
public class SecurityConfig {
	
	@Autowired
	BoardUserDetailsService boardUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}//이 클래스를 만들어서 IOC 컨테이너 등록한단 얘기. (바로 암호화 라이브러리.)
	
	
	@Bean // http.build()객체가 ioc에 등록됨
	// 로그인창 안뜨고 걍 됨. 시큐리티에서 제공하는걸 안 쓸거임!
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		security.authorizeHttpRequests(auth -> {
			//Version1
//			auth.requestMatchers("/").permitAll();
//			auth.requestMatchers("/member/**").authenticated();
			
			//Version2
			auth.requestMatchers("/member/**").authenticated() // ** : 모든 url
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // 매니저, 관리자 접근가능 = hasAnyRole(매니저, 관리자)
				.requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근가능 = hasRole(관리자)
				.anyRequest().permitAll();
		});
		
		security.userDetailsService(boardUserDetailsService);
		
//		security.csrf().disable(); //이 기능을 무효화 하겠다. 이것도 옛날버전 사라졌다. 
		security.csrf(csrf->csrf.disable()); // 포스트맨 같은데서도 접근 가능하게 해준다. csrf 보호 비활성화
		//스크립트로 url호출 못하게 막는애가 csrf 인데, 가능하도록.
		//security.cors(cors->cors.disable());
		//cors는 리액트에서 호출하는.. cors보호 비할성화.
		
		
		//security.formLogin(); //이것도 사라졌네
		//security.formLogin(form->form.defaultSuccessUrl("/")); //form 로그인을 쓰겠다! 루트(인덱스) 화면을 띄워주겠다
		//로그인 창은 뜬다 여기까진. 하지만 로그인은 안돼. 아직 에러
		security.formLogin(form->{ //여러줄일땐 이렇게
			form.loginPage("/login") //로그인 페이지로는 뭘 쓸거냐~ 겟 매핑이징 이건. 로그인 페이지 불러와야지. 
			// 로그인 컨트롤러 만들자. 이 로그인을 받아줄 컨트롤러를 만들어야. 그냥 시큐리티 컨트롤러에 만들어도 됨.
			.defaultSuccessUrl("/loginSuccess", true); //로그인 성공하면 여기로 가라.
		});
		
		security.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		security.logout(logt->{
			logt.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID"); // 로그아웃 하면 쿠키도 지운다.
				//.logoutSuccessUrl("/login"); // 로그아웃 하면 로그인 페이지가 나온다.		
		
		});

//		security.authorizeHttpRequests().ReMatchers("/").permitAll();
		
		return security.build();
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("member")  // member계정 아이디
			.password("{noop}a") //{noop} : password를 암호화 하지 않겠다는 명령어!
			.roles("MEMBER");
		auth.inMemoryAuthentication()
			.withUser("manager") // manager계정 아이디
			.password("{noop}a")
			.roles("MANAGER");
		auth.inMemoryAuthentication()
			.withUser("admin")	// admin계정 아이디
			.password("{noop}a")
			.roles("ADMIN");
	}
	
	
}
