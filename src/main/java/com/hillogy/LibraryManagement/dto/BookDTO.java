package com.hillogy.LibraryManagement.dto;

/**
 * Clase que representa un objeto de transferencia de datos (DTO) para un libro en la biblioteca.
 * Contiene información sobre el ISBN, título, autor y disponibilidad del libro.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
public class BookDTO {

	private Long ISBN;
	private String title;
	private String author;
	private boolean available;

	/**
	 * Constructor por defecto de la clase BookDTO.
	 */
	public BookDTO() {
	}

	/**
	 * Constructor con parámetros de la clase BookDTO.
	 * 
	 * @param ISBN El ISBN del libro.
	 * @param title El título del libro.
	 * @param author El autor del libro.
	 * @param available La disponibilidad del libro.
	 */
	public BookDTO(Long ISBN, String title, String author, boolean available) {
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.available = available;
	}

	/**
     * Métodos getters y setters para los atributos de la clase BookDTO.
     */
	public Long getISBN() {
		return ISBN;
	}

	public void setISBN(Long ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
