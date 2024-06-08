package br.com.ushorter.exception;

public class OriginalUrlWithInvalidFormatException extends UshorterException {

	private static final long serialVersionUID = 1L;

	public final static String MESSAGE = "error.original.url.with.invalid.format";

	public OriginalUrlWithInvalidFormatException(String message) {
		super(message);
	}

}
