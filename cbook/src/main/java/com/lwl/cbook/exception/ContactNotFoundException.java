package com.lwl.cbook.exception;

import lombok.Getter;

@Getter
public class ContactNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6705078356314223810L;
	private final String message;

	public ContactNotFoundException() {

		this.message = "Contact not present with given number";

	}

	public ContactNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ContactNotFoundException [message=" + message + "]";
	}

	


}
