package br.com.ushorter.exception;

public class RedirectUrlShortIsBlankException extends UshorterException {

	private static final long serialVersionUID = 1L;

	public final static String MESSAGE = "error.redirect.url.is.blank";

	public RedirectUrlShortIsBlankException(String message) {
		super(message);
	}
}
