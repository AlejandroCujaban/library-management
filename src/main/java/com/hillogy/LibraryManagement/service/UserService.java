package com.hillogy.LibraryManagement.service;

import java.util.List;

import com.hillogy.LibraryManagement.dto.BookDTO;
import com.hillogy.LibraryManagement.exception.BookNotFoundException;

/**
 * Interfaz para el servicio del usuario.
 * Define métodos para realizar operaciones relacionadas con los libros del usuario.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
public interface UserService {

    /**
     * Busca libros disponibles para préstamo.
     * 
     * @param available indica si se desean buscar libros disponibles o no
     * @return lista de objetos BookDTO que representan los libros disponibles
     */
    List<BookDTO> searchAvailableBooks(boolean available);
    
    /**
     * Realiza la orden de un libro para préstamo.
     * 
     * @param iSBN el ISBN del libro a ordenar
     * @return objeto BookDTO que representa el libro ordenado
     * @throws BookNotFoundException si el libro no se encuentra en la biblioteca
     */
    BookDTO orderBook(Long iSBN) throws BookNotFoundException;
    
    /**
     * Devuelve un libro que ha sido prestado.
     * 
     * @param iSBN el ISBN del libro a devolver
     * @return objeto BookDTO que representa el libro devuelto
     * @throws BookNotFoundException si el libro no se encuentra en la biblioteca
     */
    BookDTO returnBook(Long iSBN) throws BookNotFoundException;
}
