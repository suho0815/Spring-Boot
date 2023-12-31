package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j // lombok에서 제공되는 로깅
@RestController
public class HomeController {

	public HomeController() {
		System.out.println("HomeController가 생성되었습니다.");
		log.error("HomeController Error 가 생성되었습니다.");
		log.warn("HomeController Warn 가 생성되었습니다.");
		log.info("HomeController INFO 가 생성되었습니다.");
		log.debug("HomeController Debug 가 생성되었습니다.");
		log.trace("HomeController trace 가 생성되었습니다.");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "hello : " +name;
	}
}
