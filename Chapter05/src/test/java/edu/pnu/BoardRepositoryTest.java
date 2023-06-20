package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;

//	@Test
//	@Order(1)
	public void testInsertBoard() {
		for (int i = 1; i <= 100; i++) {
			Board board = new Board();
			board.setTitle("게시글" + i);
			board.setWriter("테스터" + i);
			board.setContent("잘 등록됨?");
			board.setCreateDate(new Date());
			board.setCnt(0L);

			boardRepo.save(board);
		}
	}

//	@Test
	public void testGetBoard() {

//		Optional<Board> opt = boardRepo.findById(1L);
//		Board board = opt.get();

		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}

//	@Test
	public void testUpdateBoard() {
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 전");
			System.out.println(board);
		
			board.setTitle("제목 수정");
			boardRepo.save(board);
		}
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 후");
			System.out.println(board); 
		}
	}
	
//	@Test
	public void testDeleteBoard() {
		boardRepo.deleteById(2L);
	}
	
//	@Test
	public void testfindAll() {
		List<Board> list = boardRepo.findAll();
		
		System.out.println("모든 데이터를 출력합니다.");
		for(Board d : list) {
			System.out.println(d);
		}
	}
	
//	@Test
	public void testQueryAnnotationTest1() {
		List<Board> list = boardRepo.queryAnnotationTest1("title1");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
//	@Test
	public void testQueryAnnotationTest2() {
		List<Board> list = boardRepo.queryAnnotationTest2("title1");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
//	@Test
	public void testqueryAnnotationTest3() {
		List<Object[]> list = boardRepo.queryAnnotationTest3("title1");
		for(Object[] b : list) {
			System.out.println(b);
		}
	}
	
//	@Test
	public void testqueryAnnotationTest4() {
		List<Object[]> list = boardRepo.queryAnnotationTest4("title1");
		for(Object[] b : list) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testqueryAnnotationTest5() {
//		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		Pageable paging = PageRequest.of(5, 5);
		List<Board> list = boardRepo.queryAnnotationTest5(paging);
		for(Board b : list) {
			System.out.println("----->" + b);
		}
	}

}
