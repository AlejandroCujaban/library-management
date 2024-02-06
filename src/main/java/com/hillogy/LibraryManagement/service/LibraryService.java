package com.hillogy.LibraryManagement.service;

import java.util.List;

import com.hillogy.LibraryManagement.dto.BookDTO;
import com.hillogy.LibraryManagement.exception.BookNotFoundException;


/**
 * Interfaz para el servicio de la biblioteca.
 * Define métodos para realizar operaciones relacionadas con los libros en la biblioteca.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
public interface LibraryService {

	/**
     * Crea un nuevo libro en la biblioteca.
     * 
     * @param bookDTO objeto BookDTO que representa el nuevo libro a crear
     * @return objeto BookDTO que representa el libro creado
     */
	BookDTO createNewBook(BookDTO bookDTO);

	/**
     * Obtiene un libro de la biblioteca por su ISBN.
     * 
     * @param iSBN el ISBN del libro a buscar
     * @return objeto BookDTO que representa el libro encontrado
     * @throws BookNotFoundException si el libro no se encuentra en la biblioteca
     */
	BookDTO getBookByISBN(Long iSBN) throws BookNotFoundException;

	/**
     * Obtiene todos los libros disponibles en la biblioteca.
     * 
     * @return lista de objetos BookDTO que representan todos los libros disponibles
     */
	List<BookDTO> getAllBooks();

	/**
     * Busca libros en la biblioteca por su título.
     * 
     * @param title el título del libro a buscar
     * @return lista de objetos BookDTO que representan los libros encontrados
     */
	List<BookDTO> searchBooksByTitle(String title);

	/**
     * Busca libros en la biblioteca por su autor.
     * 
     * @param author el autor del libro a buscar
     * @return lista de objetos BookDTO que representan los libros encontrados
     */
	List<BookDTO> searchBooksByAuthor(String author);

	/**
     * Elimina un libro de la biblioteca por su ISBN.
     * 
     * @param iSBN el ISBN del libro a eliminar
     * @throws BookNotFoundException si el libro no se encuentra en la biblioteca
     */
	void deleteBook(Long iSBN) throws BookNotFoundException;

}
