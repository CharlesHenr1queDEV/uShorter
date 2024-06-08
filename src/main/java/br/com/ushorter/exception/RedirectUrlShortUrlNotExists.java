package br.com.ushorter.exception;

public class RedirectUrlShortUrlNotExists extends UshorterException {

	private static final long serialVersionUID = 1L;

	public final static String MESSAGE = "error.redirect.url.short.not.exist";

	public RedirectUrlShortUrlNotExists(String message) {
		super(message);
	}
}
