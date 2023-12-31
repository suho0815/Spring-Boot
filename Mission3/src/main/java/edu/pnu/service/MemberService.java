package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDaoH2impl;
import edu.pnu.dao.MemberDaoListimpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	MemberInterface memberDao;
	
	public MemberService() {
//		memberDao = new MemberDaoListimpl();
		memberDao = new MemberDaoH2impl();
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
		
	}
	
	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
		
	}
	
	public MemberVO addMember(MemberVO member) {
		return memberDao.addMember(member);
		
	}
	
	public MemberVO updateMember(MemberVO member) {
		return memberDao.updateMember(member);
		
	}
	
	public int deleteMemeber(Integer id) {
		return memberDao.deleteMemeber(id);
		
	}
	
}
