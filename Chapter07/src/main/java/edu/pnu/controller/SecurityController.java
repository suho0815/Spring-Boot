package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/")
	public String index() {
		System.out.println("index 요청입니다.");
		return "index";
	}
	
	@GetMapping("/member")
	public String forMember() {
		System.out.println("Member 요청입니다.");
		return "member";
	}
	
	@GetMapping("/manager")
	public String forManager() {
		System.out.println("Manager 요청입니다.");
		return "manager";
	}
	
	@GetMapping("/admin")
	public String forAdmin() {
		System.out.println("Admin 요청입니다.");
		return "admin";
	}
	
	
}
