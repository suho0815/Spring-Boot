package edu.pnu;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

@SpringBootTest
@Repository
class Mission5ApplicationTests {

	@Autowired
	private DataSource dataSource;
	@Test
	public void test() {
		System.out.println(dataSource);
	}

}