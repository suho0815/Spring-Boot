package edu.pnu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequiredArgsConstructor //4번 lombok 어노테이션 이용
public class MemberController {
	//4번 lombok 어노테이션 이용
	//private final MemberService memberService;
	
	// 1번 필드에 설정하는 방법
//	@Autowired
	private MemberService memberService;
	
	//2번 생성자에서 설정하는 방법
//	@Autowired
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	//3번 Setter를 이용한 방법
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public MemberController() {
		System.out.println("MemberController가 생성되었습니다.");
		log.info("MemberController가 생성되었습니다.");
		
	}
	
	@GetMapping("/member/{id}")
	public Member getMember(@PathVariable long id) {
		return memberService.getMember(id);
	}
	
	@GetMapping("/member")
	public List<Member> getMembers(){
		return memberService.getAllMember();
	}
	
	@PostMapping("/member")
	public int insertMember(Member member) {
		System.out.println(member.getId() + " " + member.getPass() + " " + member.getName());
		return memberService.insertMember(member);
	}
	
	@PutMapping("/member")
	public int updateMember(Member member) {
		System.out.println(member.getId() + " " + member.getPass() + " " + member.getName());
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member")
	public int deleteMember(Long id) {
		return memberService.deleteMember(id);
	}
	
}
