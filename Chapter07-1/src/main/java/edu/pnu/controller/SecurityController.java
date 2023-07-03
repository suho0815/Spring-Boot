package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/")
	public String index() {
		System.out.println("index 요청입니다.");
		return "index"; 
		// Thymereaf를 쓰기로 해서 index.html을 반환한다. 
		// 하지만 해당 파일이 없을 경우에는 적혀있는 문자열을 화면에 출력한다.
	}
	
	@GetMapping("/member")
	public void froMember() {
		System.out.println("Member 요청입니다.");
	}
	
	@GetMapping("/manager")
	public void forManager() {
		System.out.println("Manager 요청입니다.");
	}
	
	@GetMapping("/admin")
	public void forAdmin() {
		System.out.println("Admain 요청입니다.");
	}
	
}
