package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;


@SpringBootTest
public class DataInitialize {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testDataInsert() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setName("짱구");
		member1.setPassword("member111");
		member1.setRole("ROLE_USER");
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setName("이수호");
		member2.setPassword("member222");
		member2.setRole("ROLE_ADMIN");
		memberRepo.save(member2);
		
		for(int i = 1; i<=3; i++) {
			Board board = new Board();
			board.setWriter("짱구");	
			board.setTitle("짱구가 등록한 글" + i);
			board.setContent("짱구가 등록한 글 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
		
		for(int i = 1; i<=3; i++) {
			Board board = new Board();
			board.setWriter("이수호");	
			board.setTitle("이수호가 등록한 간지나는 글" + i);
			board.setContent("이수호가 등록한 간지나는 글 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
		
	}
	
}
