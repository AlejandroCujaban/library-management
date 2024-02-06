package com.hillogy.LibraryManagement.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hillogy.LibraryManagement.dto.BookDTO;
import com.hillogy.LibraryManagement.exception.BookNotFoundException;
import com.hillogy.LibraryManagement.model.Book;
import com.hillogy.LibraryManagement.repository.BookRepository;
import com.hillogy.LibraryManagement.service.UserService;

/**
 * Implementación del servicio de usuario. Proporciona métodos para que los
 * usuarios interactúen con los libros.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Busca libros disponibles según la disponibilidad.
	 * 
	 * @param available indica si los libros están disponibles
	 * @return lista de objetos BookDTO que representan los libros encontrados
	 */
	@Override
	public List<BookDTO> searchAvailableBooks(boolean available) {
		List<Book> books = bookRepository.findByAvailable(available);
		return books.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	/**
	 * Marca un libro como prestado por un usuario.
	 * 
	 * @param iSBN el ISBN del libro a marcar como prestado
	 * @return objeto BookDTO que representa el libro actualizado
	 * @throws BookNotFoundException si el libro no se encuentra en la base de datos
	 */
	@Override
	public BookDTO orderBook(Long iSBN) throws BookNotFoundException {
		Book book = bookRepository.findById(iSBN).orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));
		book.setAvailable(false);
		Book updatedBook = bookRepository.save(book);
		return convertToDTO(updatedBook);
	}

	/**
	 * Marca un libro como devuelto por un usuario.
	 * 
	 * @param iSBN el ISBN del libro a marcar como devuelto
	 * @return objeto BookDTO que representa el libro actualizado
	 * @throws BookNotFoundException si el libro no se encuentra en la base de datos
	 */
	@Override
	public BookDTO returnBook(Long iSBN) throws BookNotFoundException {
		Book book = bookRepository.findById(iSBN).orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));
		book.setAvailable(true);
		Book updatedBook = bookRepository.save(book);
		return convertToDTO(updatedBook);
	}

	/**
	 * Convierte un objeto Book en un objeto BookDTO.
	 * 
	 * @param book objeto Book a convertir
	 * @return objeto BookDTO convertido
	 */
	private BookDTO convertToDTO(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setISBN(book.getiSBN());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setAvailable(book.isAvailable());
		return bookDTO;
	}

}
