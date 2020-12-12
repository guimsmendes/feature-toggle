package br.com.itau.seguros.desafio.dataprovider.exception;

public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException (String msg) {
		super(msg);
	}

}
