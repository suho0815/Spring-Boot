package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDaoH2impl;
import edu.pnu.dao.MemberDaoListimpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.LogVO;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	@Autowired
	MemberDaoH2impl memberDao;
	
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
