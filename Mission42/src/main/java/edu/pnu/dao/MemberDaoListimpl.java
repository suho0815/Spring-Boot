package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListimpl implements MemberInterface {

	List<MemberVO> list;
	
	public MemberDaoListimpl() {
		list = new ArrayList<>();
		for(int i =1; i <=5; i++) {
			list.add(new MemberVO(i, "pass" + i, "name"+i, new Date()));
		}
		
	}
	
	@Override
	public List<MemberVO> getMembers() {
		return list;
	}

	@Override
	public MemberVO getMember(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id) {
				return m;
			}
		}
		
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO member) {
		list.add(member);
		return member;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		for(MemberVO m : list) {
			if(m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return m;
			}
		}
		return null;
	}

	@Override
	public int deleteMemeber(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id) {
				list.remove(m);
				return 1;
			}
		}
		return 0;
	}

}
