package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.log.LogDao;
import edu.pnu.dao.member.MemberDaoH2impl;
import edu.pnu.dao.member.MemberDaoListimpl;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.LogVO;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	MemberInterface memberDao;
	//LogDao logdao;
	
	public MemberService() {
		//memberDao = new MemberDaoListimpl();
		memberDao = new MemberDaoH2impl();
		//logdao = new LogDao();
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}
	
	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
	}
	
	public MemberVO addMember(MemberVO member) {
		//logdao.insertLog();
		return memberDao.addMember(member);
	}
	
	public MemberVO updateMember(MemberVO member) {
		return memberDao.updateMember(member);
	}
	
	public int deleteMemeber(Integer id) {
		return memberDao.deleteMemeber(id);
	}
	
	
}
