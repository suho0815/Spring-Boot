package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 클래스가 설정 클래스라고 정의
@EnableWebSecurity // 스프링 시큐리티 활성화
public class SecurityConfig {

	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()); // csrf 보호 비활성화 (JS에서 호출 가능)
		http.cors(cors -> cors.disable()); // cors 보호 비활성화 (react에서 호출 가능)
		
		http.authorizeHttpRequests(security -> {
			security.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll();
			
		});
		
		http.formLogin(frmlogin -> {
			frmlogin.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true);
		});
		
		http.exceptionHandling(ex-> ex.accessDeniedPage("/accessDenied"));
		http.logout(logt ->{
			logt.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login");
		});
		
		
		
		return http.build();
	}
	
	
}
