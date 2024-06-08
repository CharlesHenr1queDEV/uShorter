package br.com.ushorter.exception;

public class UshorterException extends RuntimeException {

	private static final long serialVersionUID = 8906474866950846469L;

	public UshorterException(final String message) {
		super(message);
	}
}
