package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import edu.pnu.domain.LogVO;

public class LogDaoH2impl implements LogDao{
	
	private String driver;
	private String url;
	private String user;
	private String pass;
	
	Connection con;
	
	public LogDaoH2impl() {
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
	public LogVO insertLog(LogVO log) {
		String sql = "insert into dblog(method, sqlstring, success) values (?, ?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, log.getMethod());
			psmt.setString(2, log.getSqlstring());
			psmt.setBoolean(3, log.isSuccess());
			psmt.executeUpdate();
			psmt.close();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from dblog where id = (select max(id) from dblog )");
			rs.next();
			
			LogVO result = LogVO.builder()
					.id(rs.getInt("id"))
					.method(rs.getString("method"))
					.sqlstring(rs.getString("sqlstring"))
					.regidate(rs.getDate("regidate"))
					.success(rs.getBoolean("success"))
					.build();
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
