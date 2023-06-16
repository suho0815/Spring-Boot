package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPACleint {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
//		EntityTransaction tx = em.getTransaction();
//		
//		tx.begin(); // 트랜잭션 시작
//		
//		Board b = new Board();
//		b.setTitle("Title");
//		b.setWriter("Writer");
//		b.setContent("Content");
//		b.setCreateDate(new Date());
//		b.setCnt(0L);
//		
//		em.persist(b); // Manager에 저장, DB에 저장
//		
//		tx.commit();
		
		insertData(em);
		updateData(em, (long) 4, "나는 새로운 폭풍간지킹갓제네럴타이틀이다");
		deleteData(em, (long) 6);
		
		em.close();
		emf.close();
		
	}
	
	//create모드 : 기존데이터 삭제하고 새로 만듦
	//update모드 : 있는거 냅두고, 새로 만듦 
	private static void insertData(EntityManager em) { 
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			for(int i = 1; i <= 10; i++) {
				Board b = new Board();
				b.setTitle("Title" + i);
				b.setWriter("Writer" + i);
				b.setContent("Content" + i);
				b.setCreateDate(new Date());
				b.setCnt((long) i);
				em.persist(b);
			}
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}
	}
	
	private static void updateData(EntityManager em, long seq, String title) {
		
		Board b = em.find(Board.class, seq); //select
		b.setTitle(title);
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(b);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
	}
	
	private static void deleteData(EntityManager em, long seq) {
		EntityTransaction tx = em.getTransaction();
		
		Board b = em.find(Board.class, seq);
		try {
			tx.begin();
			em.remove(b);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
	}
	
	
}
