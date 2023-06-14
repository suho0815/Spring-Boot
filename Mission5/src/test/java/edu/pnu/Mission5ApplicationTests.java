package edu.pnu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

@SpringBootTest
class Mission5ApplicationTests {

	@Autowired
	private DataSource dataSource;
	@Test
	public void test() throws SQLException {
		Statement st = dataSource.getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from member");
		while(rs.next()) {
			System.out.println(rs.getInt("id"));
		}
	}

}
