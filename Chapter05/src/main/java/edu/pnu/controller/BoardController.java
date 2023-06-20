package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class BoardController {

	@Autowired
	BoardRepository boardRepo;
	
	@GetMapping("/getBoardList")
	public List<Board> getBoardList(){
		return boardRepo.findAll();
	}
	
	@GetMapping("/board")
	public Board getBoard(Long id) {
		return boardRepo.findById(id).get();
	}
	
	@PostMapping("/board")
	public Board insertBoard(Board b) {
		if (b.getCreateDate() == null)
			b.setCreateDate(new Date());
		return boardRepo.save(b);
	}
	
	@PostMapping("/boardjson")
	public Board insertJsonBoard(@RequestBody Board b) {
		if (b.getCreateDate() == null)
			b.setCreateDate(new Date());
		return boardRepo.save(b);
	}
	
	@PutMapping("/board")
	public Board updateBoard(Board b) {
		return boardRepo.save(b);
	}
	
	@DeleteMapping("/board")
	public String deleteBoard(Long id) {
		boardRepo.deleteById(id);
		return String.format("seq가 %d인 데이터가 삭제됨", id);
	}
	//페이징
//	@GetMapping("/boardpage")
//	public Page<Board> findPage() {
//		Pageable pageing = PageRequest.of(0, 5);
//		return boardRepo.findAll(pageing);
//	}
	
	
	@GetMapping("/boardtitlecontain")
	public List<Board> findByTitleContaining(String title) {
		Pageable page = PageRequest.of(0, 5);
		return boardRepo.findBoardByTitleContaining(title, page);
	}
	
	@GetMapping("/boardtitlecontain2")
	public List<Board> findBoardByTitleContainingAndGreaterThan(String title, Long a) {
		return boardRepo.findBoardByTitleContainingAndCntGreaterThanEqual(title, a);
	}
	
	@GetMapping("/boardcntbetween")
	public List<Board> findBoardByCntGreaterThanEqualAndCntLessThanEqualOrderByCntAsc(Long a, Long b) {
		return boardRepo.findBoardByCntGreaterThanEqualAndCntLessThanEqualOrderByCntAsc(a, b);
	}
	
	@GetMapping("/boardtitlecontent")
	public List<Board> findBoardByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content) {
		return boardRepo.findBoardByTitleContainingOrContentContainingOrderBySeqDesc(title, content);
	}
	
}
