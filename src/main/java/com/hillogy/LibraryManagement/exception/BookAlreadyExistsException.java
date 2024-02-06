package com.hillogy.LibraryManagement.exception;

/**
 * Excepción lanzada cuando se intenta agregar un libro que ya existe.
 * Extiende de RuntimeException para indicar que es una excepción no verificada.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
public class BookAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que acepta un mensaje de error.
	 * 
	 * @param message El mensaje que describe la excepción.
	 */
	public BookAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * Constructor que acepta un mensaje de error y una causa subyacente.
	 * 
	 * @param message El mensaje que describe la excepción.
	 * @param cause La causa subyacente de la excepción.
	 */
	public BookAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}