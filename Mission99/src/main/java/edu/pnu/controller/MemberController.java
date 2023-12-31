package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
//	public MemberController() {
//		//memberService = new MemberService();
//	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		return memberService.getMember(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO member) {
		return memberService.addMember(member);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO member) {
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public int deleteMemeber(@PathVariable Integer id) {
		return memberService.deleteMemeber(id);
	}
	
}
