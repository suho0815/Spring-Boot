package edu.pnu.domain;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	
	@Id
	private String username;
	private String password;
	private String role;
	private boolean enabled;
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//version1
		//return AuthorityUtils.createAuthorityList(role);
	
		//version2
		Collection<GrantedAuthority> list = new ArrayList<>();
		list.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return role;
			}
		});
		return list;
	
	}

	
}
