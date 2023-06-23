package edu.pnu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
	
	@Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long id;
	private String id;
	private String password;
	private String name;
	private String role;
	
}
