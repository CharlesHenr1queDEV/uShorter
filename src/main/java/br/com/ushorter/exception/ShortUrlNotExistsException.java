package br.com.ushorter.exception;

public class ShortUrlNotExistsException extends UshorterException {

	private static final long serialVersionUID = 1L;

	public final static String MESSAGE = "error.short.url.not.exist";
	
	public ShortUrlNotExistsException(String message) {
		super(message);
	}


}
