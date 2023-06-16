package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.service.TestService1;
import edu.pnu.service.TestService2;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	
//	@Autowired
//	private TestService1 testService1;
//	
//	@Autowired
//	private TestService2 testService2;
//	
//	public TestController() {
//		System.out.println("TestController");
//	}
	
	private final TestService1 testService1;
	private final TestService2 testService2;
	
//	@Autowired
//	public TestController(TestService1 testService1, TestService2 testService2) {
//		this.testService1 = testService1;
//		this.testService2 = testService2;
//		
//		System.out.println("TestController");
//	}
	
	@GetMapping({"/", "/home"})
	public String home() {
		return "Home";
	}
	
	@GetMapping("/test")
	public String test() {
		return testService1.test();
	}
	
}
