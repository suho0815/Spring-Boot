package edu.pnu.exception;

public class UsenameNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UsenameNotFoundException(String message) {
		super(message);
	}
	
}
