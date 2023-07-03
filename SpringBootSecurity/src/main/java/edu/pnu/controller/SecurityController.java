package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping({"/", "/index"}) // /와 /index를 Get요청하면?
	public String index() {
		System.out.println("index 요청입니다.");
		return "index";
	}
	
	@GetMapping("/member")
	public String forMember() {
		System.out.println("member 요청입니다.");
		return "member";
	}
	
	@GetMapping("/manager")
	public String forManager() {
		System.out.println("manager 요청입니다.");
		return "manager";
	}
	
	@GetMapping("/admin")
	public String forAdmin() {
		System.out.println("admin 요청입니다.");
		return "admin";
	}
	
	
}
