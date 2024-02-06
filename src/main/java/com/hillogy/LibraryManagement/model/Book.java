package com.hillogy.LibraryManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa un libro en el sistema de gestión de la biblioteca.
 * Contiene información como el título, el autor y la disponibilidad del libro.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
@Entity
@Table(name = "tb_books")
public class Book {

	
    private Long iSBN;
    private String title;
    private String author;
    private boolean available;

    /**
     * Constructor por defecto de la clase Book.
     */
    public Book() {

    }

    /**
     * Constructor de la clase Book que acepta parámetros para inicializar los campos.
     * 
     * @param title El título del libro.
     * @param author El autor del libro.
     * @param available Indica si el libro está disponible.
     */
	public Book( String title, String author, boolean available) {
		this.title = title;
		this.author = author;
		this.available = available;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getiSBN() {
		return iSBN;
	}

	public void setiSBN(Long iSBN) {
		this.iSBN = iSBN;
	}
	
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "author", nullable = false)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "available", nullable = false)
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	
}