package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {

	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "scott";
	private String password = "tiger";
	
	private Connection con;
	
	public MemberDao() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Member getMember(Long id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d", id));
			
			rs.next();
			
			Member m = Member.builder()
					.id(rs.getLong("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			
			rs.close();
			stmt.close();
			
			return m;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Member> getAllMember(){
		List<Member> list = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member"));
			
			while(rs.next()) {
				Member m = Member.builder()
						.id(rs.getLong("id"))
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
	
	public int insertMember(Member member) {
		
		try {
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
			String sql = "insert into member(pass, name) values (?, ?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			int rs = psmt.executeUpdate();
			
			//Member m = getMember(member.getId());
			
			psmt.close();
			
			return rs;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int updateMember(Member member) {
		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(String.format("UPDATE member SET pass='%s', name='%s' WHERE id=%d", member.getPass(), member.getName(), member.getId()));
			
			stmt.close();
			
			return rs;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int deleteMember(Long id) {
		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(String.format("delete from member where id=%d", id));
			
			stmt.close();
			
			return rs;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
