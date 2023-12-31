package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2impl implements MemberInterface{

	private String driver;
	private String url;
	private String user;
	private String pass;
	
	Connection con;
	
	public MemberDaoH2impl() {
		driver = "org.h2.Driver";
		url = "jdbc:h2:tcp://localhost/~/mission3";
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
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from member order by id");
			
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
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO getMember(Integer id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from member where id=%d", id));
			rs.next();
			
			MemberVO m = MemberVO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			
			rs.close();
			stmt.close();
			return m;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO member) {
		try {
			String sql = "insert into member(pass, name) values (?, ?)";
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
			return m;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		try {
			String sql = "update member set pass=?, name=? where id=?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.setInt(3, member.getId());
			psmt.executeUpdate();
			
			psmt.close();
			return getMember(member.getId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteMemeber(Integer id) {
		try {
			Statement stmt = con.createStatement();
			int result = stmt.executeUpdate(String.format("delete from member where id=%d", id));
			stmt.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
