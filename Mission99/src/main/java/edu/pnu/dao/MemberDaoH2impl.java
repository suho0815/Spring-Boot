package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;
@Repository
public class MemberDaoH2impl implements MemberInterface{

	JdbcTemplate jdbcTemplate;
	@Autowired
	public MemberDaoH2impl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<MemberVO> getMembers() {
		String query = "select * from member order by id";
		List<MemberVO> list = jdbcTemplate.query(query, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mv = new MemberVO();
				mv.setId(rs.getInt("id"));
				mv.setName(rs.getString("Name"));
				mv.setPass(rs.getString("pass"));
				mv.setRegidate(rs.getDate("regidate"));
				
				return mv;
			}
		});
		return list;
	}

	@Override
	public MemberVO getMember(Integer id) {
		String query = "select * from member where id=?";
		MemberVO m = (MemberVO) jdbcTemplate.query(query, new BeanPropertyRowMapper<MemberVO>(MemberVO.class)); 
		return m;
	}

	@Override
	public MemberVO addMember(MemberVO member) {
		String query = "insert into member(pass, name) values (?, ?)";
		MemberVO m = (MemberVO) jdbcTemplate.query(query, new BeanPropertyRowMapper<MemberVO>(MemberVO.class)); 
		return m;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		String query = "update member set pass=?, name=? where id=?";
		MemberVO m = (MemberVO) jdbcTemplate.query(query, new BeanPropertyRowMapper<MemberVO>(MemberVO.class)); 
		return m;
	}

	@Override
	public int deleteMemeber(Integer id) {
		String query = "delete from member where id=?";
		MemberVO m = (MemberVO) jdbcTemplate.query(query, new BeanPropertyRowMapper<MemberVO>(MemberVO.class)); 
		return 1;
	}

}
