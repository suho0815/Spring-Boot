package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Component
public class BoardDataInitalizer implements ApplicationRunner {

	@Autowired
	BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Random rd = new Random();
		for (int i = 1; i <= 100; i++) {
			
			Board board = new Board();
			board.setTitle("간지나는 제목" + i);
			board.setWriter("이수호" + i);
			board.setContent("간지나는 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(rd.nextLong(0, 101));

			boardRepo.save(board);
		}

	}

}
