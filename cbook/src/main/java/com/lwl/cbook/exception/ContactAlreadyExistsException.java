package com.lwl.cbook.exception;

import lombok.Getter;

@Getter
public class ContactAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5220975146106586150L;

	private final String message;

	public ContactAlreadyExistsException() {

		this.message = "Contact already exists with given number";

	}

	public ContactAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ContactAlreadyExistsException [message=" + message + "]";
	}

}
