package com.hillogy.LibraryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hillogy.LibraryManagement.model.Book;

/**
 * Repositorio para acceder a la entidad Book en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	/**
     * Busca libros cuyo título contenga la cadena especificada, ignorando mayúsculas y minúsculas.
     * 
     * @param title El título (o parte del título) a buscar.
     * @return Lista de libros que coinciden con el título especificado.
     */
	List<Book> findByTitleContainingIgnoreCase(String title);

	/**
     * Busca libros cuyo autor contenga la cadena especificada, ignorando mayúsculas y minúsculas.
     * 
     * @param author El autor (o parte del autor) a buscar.
     * @return Lista de libros cuyo autor coincide con el especificado.
     */
	List<Book> findByAuthorContainingIgnoreCase(String author);

	/**
     * Busca libros disponibles o no disponibles según el parámetro booleano especificado.
     * 
     * @param available Indica si se desean libros disponibles (true) o no disponibles (false).
     * @return Lista de libros que están disponibles o no disponibles según el parámetro.
     */
	List<Book> findByAvailable(boolean available);

	/**
     * Verifica si existe un libro con el título y autor especificados.
     * 
     * @param title El título del libro a verificar.
     * @param author El autor del libro a verificar.
     * @return true si existe un libro con el título y autor especificados, false de lo contrario.
     */
	boolean existsByTitleAndAuthor(String title, String author);

}
