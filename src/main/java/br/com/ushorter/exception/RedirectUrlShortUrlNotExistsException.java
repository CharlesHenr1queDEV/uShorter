package br.com.ushorter.exception;

public class RedirectUrlShortUrlNotExistsException extends UshorterException {

	private static final long serialVersionUID = 1L;

	public final static String MESSAGE = "error.redirect.url.short.not.exist";

	public RedirectUrlShortUrlNotExistsException(String message) {
		super(message);
	}
}
