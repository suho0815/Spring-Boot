package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // @configuration을 쓰는 이유는? >> 해당 클래스에서 Bean을 만들어줘야 하기 때문이다.
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	BoardUserDetailsService boardUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder(); // 암호화 메소드
	}
	
	
	@Bean //@Bean이 선언되어 있는 메소드의 반환값은 IOC 컨테이너에 등록되기 때문에 외부에서 반환된 데이터 사용이 가능하다.
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		security.authorizeHttpRequests(auth->{
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
		
		security.csrf(csrf->csrf.disable());// CSRF 보호 비활성화 (JS에서 호출 가능) 
											//ex) <a href="url"></a> 가능하다.
		
		security.formLogin(form-> {	
			form.loginPage("/login") // "/login"만 로그인 페이지가 뜨고 login.html을 호출한다.
				.defaultSuccessUrl("/loginSuccess", true); // 로그인에 성공하면 loginSuccess로 간다.
				//.defaultSuccessUrl("/"); // defaultSuccessUrl("/")제외하고 로그인 창을 뜨게 만든다.
		});
		
		security.exceptionHandling(ex -> ex.accessDeniedPage("/accessDenied"));
		
		security.logout(logt -> {
			logt.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID"); // 로그아웃 하면 쿠키도 지운다.
				//.logoutSuccessUrl("/login"); // 로그아웃 하면 로그인 페이지가 나온다.
			});
		
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


// 해당 클래스의 목적은 IOC컨테이너에 올려서 로그인 기능을 사용하기않기 위함이다.
