package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login") // url이 /login이면
	public void login() {} // 반환 타입이 void라서 바로 login.html을 실행한다.
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {}
}
