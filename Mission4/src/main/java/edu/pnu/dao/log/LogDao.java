package edu.pnu.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import edu.pnu.domain.LogVO;

public interface LogDao {
	
	public LogVO insertLog(LogVO log);
	
}
