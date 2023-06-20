package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	Page<Board> findAll(Pageable paging);
	
	
	List<Board> findBoardByTitleContaining(String title, Pageable paging);
	
	List<Board> findBoardByTitleContainingAndCntGreaterThanEqual(String title, Long a);
	
	List<Board> findBoardByCntGreaterThanEqualAndCntLessThanEqualOrderByCntAsc(Long a, Long b);
	
	List<Board> findBoardByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	
}
