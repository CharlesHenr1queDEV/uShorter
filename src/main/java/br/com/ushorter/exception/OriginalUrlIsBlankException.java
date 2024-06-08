package br.com.ushorter.exception;

public class OriginalUrlIsBlankException extends UshorterException {

	private static final long serialVersionUID = 1L;

	public final static String MESSAGE = "error.original.url.is.blank";

	public OriginalUrlIsBlankException(String message) {
		super(message);
	}

}
