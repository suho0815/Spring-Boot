package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {

	private Connection con;
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/test";
	private String user = "scott";
	private String pass = "tiger";
	
	//DB Connection 설정
	public MemberDao() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int insertMember(Member m) {
		
		try {
			String sql = "insert into member (name, age, nickname) values (?, ?, ?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, m.getName());
			psmt.setInt(2, m.getAge());
			psmt.setString(3, m.getNickname());
			int result = psmt.executeUpdate();
			
			psmt.close();
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<Member> getMembers(){
		List<Member> list = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from member order by id");
			
			while(rs.next()) {
				Member m = Member.builder()
						.id(rs.getLong("Id"))
						.name(rs.getString("Name"))
						.age(rs.getInt("age"))
						.nickname(rs.getString("nickname"))
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
	
	public Member getMember(Long id){
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from member where Id=%d", id));
			rs.next();
			
			Member m = Member.builder()
					.id(rs.getLong("Id"))
					.name(rs.getString("Name"))
					.age(rs.getInt("age"))
					.nickname(rs.getString("nickname"))
					.build();
			
			rs.close();
			stmt.close();
			return m;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
