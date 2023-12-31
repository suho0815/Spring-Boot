package edu.pnu.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController() {
		System.out.println("MemberController가 생성되었습니다.");
		log.info("MemberController가 생성되었습니다.");
		
		memberService = new MemberService();
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
