package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDaoH2impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;
@Service
public class MemberService {
	
	MemberInterface memberDao;
	
	@Autowired
	public MemberService() {
//		memberDao = new MemberDaoListimpl();
		memberDao = new MemberDaoH2impl(null);
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
