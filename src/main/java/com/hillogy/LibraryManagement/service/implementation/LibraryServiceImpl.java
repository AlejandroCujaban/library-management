package com.hillogy.LibraryManagement.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hillogy.LibraryManagement.dto.BookDTO;
import com.hillogy.LibraryManagement.exception.BookAlreadyBorrowedException;
import com.hillogy.LibraryManagement.exception.BookAlreadyExistsException;
import com.hillogy.LibraryManagement.exception.BookNotFoundException;
import com.hillogy.LibraryManagement.model.Book;
import com.hillogy.LibraryManagement.repository.BookRepository;
import com.hillogy.LibraryManagement.service.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de la biblioteca.
 * Proporciona métodos para realizar operaciones relacionadas con los libros.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Recupera todos los libros de la base de datos.
	 * 
	 * @return lista de objetos BookDTO que representan los libros
	 */
	@Override
	public List<BookDTO> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	/**
	 * Crea un nuevo libro en la biblioteca.
	 * 
	 * @param bookDTO objeto BookDTO que representa el libro a crear
	 * @return objeto BookDTO que representa el libro creado
	 * @throws BookAlreadyExistsException si el libro ya existe en la base de datos
	 */
	@Override
	public BookDTO createNewBook(BookDTO bookDTO) throws BookAlreadyExistsException {
		// Verificar si el libro ya existe en la base de datos
		if (bookRepository.existsByTitleAndAuthor(bookDTO.getTitle(), bookDTO.getAuthor())) {
			throw new BookAlreadyExistsException("El libro ya existe en la base de datos");
		}

		// Si el libro no existe, se crea y se guarda en el repositorio
		Book book = convertToEntity(bookDTO);
		Book savedBook = bookRepository.save(book);
		return convertToDTO(savedBook);
	}

	/**
	 * Recupera un libro por su ISBN.
	 * 
	 * @param iSBN el ISBN del libro a recuperar
	 * @return objeto BookDTO que representa el libro encontrado
	 * @throws BookNotFoundException si el libro no se encuentra en la base de datos
	 */
	@Override
	public BookDTO getBookByISBN(Long iSBN) throws BookNotFoundException {
		Book book = bookRepository.findById(iSBN).orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));
		return convertToDTO(book);
	}

	/**
	 * Elimina un libro de la biblioteca por su ISBN.
	 * 
	 * @param iSBN el ISBN del libro a eliminar
	 * @throws BookNotFoundException si el libro no se encuentra en la base de datos
	 * @throws BookAlreadyBorrowedException si el libro está prestado y no se puede eliminar
	 */
	@Override
	public void deleteBook(Long iSBN) throws BookNotFoundException {
		Book book = bookRepository.findById(iSBN).orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));
		if (!book.isAvailable()) {
			throw new BookAlreadyBorrowedException("No se puede borrar el libro porque está prestado.");
		}
		bookRepository.delete(book);
	}

	/**
	 * Busca libros por su título.
	 * 
	 * @param title el título del libro a buscar
	 * @return lista de objetos BookDTO que representan los libros encontrados
	 */
	@Override
	public List<BookDTO> searchBooksByTitle(String title) {
		List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
		return books.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	/**
	 * Busca libros por su autor.
	 * 
	 * @param author el autor del libro a buscar
	 * @return lista de objetos BookDTO que representan los libros encontrados
	 */
	@Override
	public List<BookDTO> searchBooksByAuthor(String author) {
		List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
		return books.stream().map(this::convertToDTO).collect(Collectors.toList());
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

	/**
	 * Convierte un objeto BookDTO en un objeto Book.
	 * 
	 * @param bookDTO objeto BookDTO a convertir
	 * @return objeto Book convertido
	 * 
	 */
	private Book convertToEntity(BookDTO bookDTO) {
		Book book = new Book();
		book.setiSBN(bookDTO.getISBN());
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setAvailable(bookDTO.isAvailable());
		return book;
	}
}