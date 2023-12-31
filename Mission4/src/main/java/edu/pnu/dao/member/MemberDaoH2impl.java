package edu.pnu.dao.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.dao.log.LogDaoH2impl;
import edu.pnu.domain.LogVO;
import edu.pnu.domain.MemberVO;

public class MemberDaoH2impl implements MemberInterface{

	private String driver;
	private String url;
	private String user;
	private String pass;
	
	Connection con;
	
	LogDaoH2impl logdao;
	
	public MemberDaoH2impl() {
		logdao = new LogDaoH2impl();
		
		driver = "org.h2.Driver";
		url = "jdbc:h2:tcp://localhost/~/mission4";
		user = "scott";
		pass = "tiger";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		String sql = "select * from member order by id";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO m = MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				list.add(m);
			}
			rs.close();
			stmt.close();
			
			LogVO log = new LogVO();
			log.setMethod("getMembers");
			log.setSqlstring(sql);
			log.setSuccess(true);
			
			logdao.insertLog(log);
			return list;
		}catch(Exception e) {
			LogVO log = new LogVO();
			log.setMethod("getMembers");
			log.setSqlstring(sql);
			log.setSuccess(false);
			
			logdao.insertLog(log);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO getMember(Integer id) {
		String sql = String.format("select * from member where id=%d", id);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			MemberVO m = MemberVO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			
			rs.close();
			stmt.close();
			LogVO log = new LogVO();
			log.setMethod("getMember");
			log.setSqlstring(sql);
			log.setSuccess(true);
			logdao.insertLog(log);
			return m;
		}catch(Exception e) {
			LogVO log = new LogVO();
			log.setMethod("getMember");
			log.setSqlstring(sql);
			log.setSuccess(false);
			logdao.insertLog(log);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO member) {
		String sql = "insert into member(pass, name) values (?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.executeUpdate();
			
			psmt.close();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from member where id = (select max(id) from member)");
			rs.next();
			MemberVO m = MemberVO.builder()
					.id(rs.getInt("id")) 
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			
			rs.close();
			stmt.close();
			
			LogVO log = new LogVO();
			log.setMethod("addMember");
			log.setSqlstring(sql);
			log.setSuccess(true);
			logdao.insertLog(log);
			return m;
		}catch(Exception e) {
			LogVO log = new LogVO();
			log.setMethod("addMember");
			log.setSqlstring(sql);
			log.setSuccess(false);
			logdao.insertLog(log);
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		String sql = "update member set pass=?, name=? where id=?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.setInt(3, member.getId());
			psmt.executeUpdate();
			
			psmt.close();
			
			LogVO log = new LogVO();
			log.setMethod("updateMemb");
			log.setSqlstring(sql);
			log.setSuccess(true);
			logdao.insertLog(log);
			
			return getMember(member.getId());
		}catch(Exception e) {
			LogVO log = new LogVO();
			log.setMethod("updateMemb");
			log.setSqlstring(sql);
			log.setSuccess(false);
			logdao.insertLog(log);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteMemeber(Integer id) {
		String sql = String.format("delete from member where id=%d", id);
		try {
			Statement stmt = con.createStatement();
			int result = stmt.executeUpdate(sql);
			stmt.close();
			LogVO log = new LogVO();
			log.setMethod("deleteMemb");
			log.setSqlstring(sql);
			log.setSuccess(true);
			logdao.insertLog(log);
			return result;
		}catch(Exception e) {
			LogVO log = new LogVO();
			log.setMethod("deleteMemb");
			log.setSqlstring(sql);
			log.setSuccess(false);
			logdao.insertLog(log);
			e.printStackTrace();
		}
		
		return 0;
	}

}
