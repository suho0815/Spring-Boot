package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogDao;
import edu.pnu.dao.log.LogDaoH2Impl;
import edu.pnu.dao.member.MemberDaoH2Impl;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	@Autowired //다형성 적용 됨 (상속받은 것도 가져올 수 있음)
	//@Qualifier("빈 이름") //@Component("obj1") 어노테이션을 이용하여 빈 이름 지정하여 주입
	private MemberInterface memberDao;
	
	@Autowired
	private LogDao logDao;
	
	public MemberService() {
	      memberDao = new MemberDaoH2Impl();
	      // memberDao = new MemberDaoListImpl();

	      logDao = new LogDaoH2Impl();
	      // logDao = new LogDaoFileImpl();
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers() {
		Map<String, Object> map = memberDao.getMembers();
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		if (list != null)	logDao.addLog("get", (String)map.get("sql"), true);
		else				logDao.addLog("get", (String)map.get("sql"), false);
		return list;
	}

	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		MemberVO member = (MemberVO) map.get("data");
		if (member != null)	logDao.addLog("get", (String)map.get("sql"), true);
		else				logDao.addLog("get", (String)map.get("sql"), false);
		return member;
	}

	public MemberVO addMember(MemberVO member) {
		Map<String, Object> map = memberDao.addMember(member);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("post", (String)map.get("sql"), true);
		else			logDao.addLog("post", (String)map.get("sql"), false);
		return m;		
	}

	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> map = memberDao.updateMember(member);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("put", (String)map.get("sql"), true);
		else			logDao.addLog("put", (String)map.get("sql"), false);	
		return m;
	}

	public MemberVO deleteMember(Integer id) {
		Map<String, Object> map = memberDao.deleteMember(id);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("delete", (String)map.get("sql"), true);
		else			logDao.addLog("delete", (String)map.get("sql"), false);
		return m;
	}
}
