package edu.pnu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	private void test(String searchCondition, String searchKeyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Board> list = boardRepo.findAll(builder, paging);
		
		for(Board b : list) {
			System.out.println("--->" + b);
		}
	}
	
	private void test1(Map<String, String> map) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		Set<String> keys = map.keySet();
		for(String key : keys) {
			
			if(key.equals("TITLE")) {
				builder.and(qboard.title.like("%" + map.get(key) + "%"));
			} else if(key.equals("CONTENT")) {
				builder.and(qboard.content.like("%" + map.get(key) + "%"));
			}
			Pageable paging = PageRequest.of(0, 5);
			
			Page<Board> list = boardRepo.findAll(builder, paging);
			
			for(Board b : list) {
				System.out.println("--->" + b);
			}
		}
		
	}
	
	
	private void test2(Map<String, String> map) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		Set<String> keys = map.keySet();
		for(String key : keys) {
			
			if(key.equals("TITLE")) {
				builder.and(qboard.title.like("%" + map.get(key) + "%"));
				//.and(qboard.cnt.goe(Long.parseLong(map.get(key))));
			} 
			else if(key.equals("CNT")) {
				builder.and(qboard.cnt.goe(Long.parseLong(map.get(key))));
			}
			Pageable paging = PageRequest.of(0, 5);
			
			Page<Board> list = boardRepo.findAll(builder, paging);
			
			for(Board b : list) {
				System.out.println("--->" + b);
			}
		}
		
	}
	
	
	@Test
	public void testDynamicQuery() {
//		test("TITLE", "1");
//		test("CONTENT", "2");
		
		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "간지나는 제목1");
		map.put("CONTENT", "간지나는 내용2");
		map.put("CNT", "50");
		test2(map);
	}
	
	
}
