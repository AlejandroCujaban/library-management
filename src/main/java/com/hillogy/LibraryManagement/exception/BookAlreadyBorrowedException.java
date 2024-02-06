package com.hillogy.LibraryManagement.exception;

/**
 * Excepción lanzada cuando se intenta borrar un libro que ya ha sido prestado.
 * Extiende de RuntimeException para indicar que es una excepción no verificada.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
public class BookAlreadyBorrowedException extends RuntimeException {

	/**
     * Constructor que acepta un mensaje de error.
     * 
     * @param message El mensaje que describe la excepción.
     */
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje de error y una causa subyacente.
     * 
     * @param message El mensaje que describe la excepción.
     * @param cause La causa subyacente de la excepción.
     */
    public BookAlreadyBorrowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
