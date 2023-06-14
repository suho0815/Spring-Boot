package edu.pnu.dao.member;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	
	List<MemberVO> getMembers();
	
	MemberVO getMember(Integer id);
	
	MemberVO addMember(MemberVO member);
	
	MemberVO updateMember(MemberVO member);
	
	int deleteMemeber(Integer id);
	
}
