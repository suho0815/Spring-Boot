package edu.pnu.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

/**
 * 
 */

@Service // 이래야 IOC에 등록, 부트 시큐리티에서 땡겨쓰겄지
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	MemberRepository memRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Member> option = memRepo.findById(username); // 옵셔날~ 타입으로 받네
		if(!option.isPresent()) {
			throw new UsernameNotFoundException("사용자가 없습니다.");// 이거 쓰든 리턴 null 하든.
		//	return null;
		}
		
		Member member = option.get();//옵션 겟 하면 멤버 데이터 갖고옴
		
		System.out.println(member); //잘 가져오나 여기까지만 하고 확인해보자
		
		//import org.spring
		return new User(member.getUsername(), member.getPassword(), member.getAuthorities());
		//권한들. 시스템 덩치 커지면 한사람이 권한 여러개 가질수 있으니까 권한들. 복수개의 리스트로 입력받는다.
		
	}

}