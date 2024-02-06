package com.hillogy.LibraryManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no se puede encontrar un libro en el sistema.
 * Está anotada con @ResponseStatus para devolver un estado HTTP 404 (NOT FOUND) cuando se lance.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
     * Constructor que acepta un mensaje de error.
     * 
     * @param message El mensaje que describe la excepción.
     */
	public BookNotFoundException(String message){
        super(message);
    }

	/**
     * Constructor que acepta un mensaje de error y una causa subyacente.
     * 
     * @param message El mensaje que describe la excepción.
     * @param cause La causa subyacente de la excepción.
     */
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
