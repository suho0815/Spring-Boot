package edu.pnu.dao.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl implements MemberInterface {
	@Autowired
	private DataSource datasource;
	
	@Override
	public Map<String, Object> getMembers() {
		Statement st = null;
		ResultSet rs = null;
		String sqlString = "select * from member order by id asc";
		try (Connection con = datasource.getConnection()){
			List<MemberVO> list = new ArrayList<>();
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			while(rs.next() ) {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setRegidate(rs.getDate("regidate"));
				list.add(m);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		Statement st = null;
		ResultSet rs = null;
		try {
			String sqlString = String.format("select * from member where id=%d", id);
			st = datasource.getConnection().createStatement();
			rs = st.executeQuery(sqlString);
			rs.next();
			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setPass(rs.getString("pass"));
			m.setName(rs.getString("name"));
			m.setRegidate(rs.getDate("regidate"));
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sqlString);
			map.put("data", m);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private int getNextId() {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = datasource.getConnection().createStatement();
			rs = st.executeQuery("select max(id) from member");
			rs.next();
			return rs.getInt(1) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;		
	}
	
	@Override
	public Map<String, Object> addMember(MemberVO member) {
		
		int id = getNextId();
		
		Statement st = null;
		try {
			String sqlString = String.format("insert into member (id,name,pass,regidate) values (%d,'%s','%s','%s')",
				id, member.getName(), member.getPass(), new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
			st = datasource.getConnection().createStatement();

			Map<String, Object> ret = new HashMap<>();
			if (st.executeUpdate(sqlString) == 1) {
				Map<String, Object> map = getMember(id);
				ret.put("sql", sqlString);
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("sql", sqlString);
				ret.put("data", null);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		Statement st = null;
		try {
			String sqlString = String.format("update member set name='%s',pass='%s' where id=%d",
					member.getName(), member.getPass(), member.getId());
			st = datasource.getConnection().createStatement();
			
			Map<String, Object> ret = new HashMap<>();
			if (st.executeUpdate(sqlString) == 1) {
				Map<String, Object> map = getMember(member.getId());
				ret.put("sql", sqlString);
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("sql", sqlString);
				ret.put("data", null);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		Statement st = null;
		try {
			String sqlString = String.format("delete from member where id=%d", id);
			st = datasource.getConnection().createStatement();

			Map<String, Object> map = getMember(id);
			if (map.get("data") == null) 
				return null;

			Map<String, Object> ret = new HashMap<>();
			if (st.executeUpdate(sqlString) == 1) {
				ret.put("sql", sqlString);
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("sql", sqlString);
				ret.put("data", null);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
