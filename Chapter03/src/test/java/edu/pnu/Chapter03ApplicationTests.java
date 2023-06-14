package edu.pnu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


//@WebMvcTest // controller만 테스트(@Controller, @RestController가 붙은 클래스들을 찾아 메모리에 생성함)
@SpringBootTest
class Chapter03ApplicationTests {

	// 서블릿 컨테이너 모킹(실제 객체와 비슷한 모의 객체를 만듦)
//	@Autowired
//	MockMvc mockMvc;
	
	@Test
	void contextLoads() throws Exception {
		System.out.println("contextLoads");
//		mockMvc.perform(get("/hello").param("name", "둘리"))
//			.andExpect(status().isOk())
//			//.andExpect(content().string("Hello":"둘리"))
//			.andDo(print());
	}

}
