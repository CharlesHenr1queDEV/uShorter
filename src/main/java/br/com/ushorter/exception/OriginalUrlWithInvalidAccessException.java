package br.com.ushorter.exception;

public class OriginalUrlWithInvalidAccessException extends UshorterException {

	private static final long serialVersionUID = 1L;

	public final static String MESSAGE = "error.original.url.with.invalid.access";

	public OriginalUrlWithInvalidAccessException(String message) {
		super(message);
	}

}
