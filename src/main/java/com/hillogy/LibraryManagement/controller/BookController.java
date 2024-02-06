package com.hillogy.LibraryManagement.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hillogy.LibraryManagement.dto.BookDTO;
import com.hillogy.LibraryManagement.exception.BookAlreadyExistsException;
import com.hillogy.LibraryManagement.exception.BookNotFoundException;
import com.hillogy.LibraryManagement.service.LibraryService;
import com.hillogy.LibraryManagement.service.UserService;

import jakarta.validation.Valid;

/**
 * Controlador REST que gestiona las operaciones relacionadas con los libros en la biblioteca.
 * Permite realizar acciones como buscar libros, añadir nuevos libros, eliminar libros,
 * ordenar libros y devolver libros prestados.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/books")
public class BookController {


	@Autowired
	private LibraryService libraryService;

	@Autowired
	private UserService userService;

	/**
	 * Endpoint para buscar libros según el título, autor o ISBN.
	 * 
	 * @param title Título del libro a buscar (opcional).
	 * @param author Autor del libro a buscar (opcional).
	 * @param isbn ISBN del libro a buscar (opcional).
	 * @return Una lista de libros que coinciden con los parámetros de búsqueda.
	 * @throws BookNotFoundException Si no se encuentran libros que coincidan con la búsqueda.
	 */
	@GetMapping("/library/search")
	public List<BookDTO> searchBooks(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) Long isbn
			) throws BookNotFoundException {
		if (title != null) {
			return libraryService.searchBooksByTitle(title);
		} else if (author != null) {
			return libraryService.searchBooksByAuthor(author);
		} else if (isbn != null) {
			// En caso de buscar por ISBN, devolvemos una lista con un solo libro
			BookDTO book = libraryService.getBookByISBN(isbn);
			return Collections.singletonList(book);
		} else {
			// Si ninguno de los parámetros de búsqueda está presente, retornamos todos los libros
			return libraryService.getAllBooks();
		}
	}

	/**
	 * Endpoint para añadir un nuevo libro a la biblioteca.
	 * 
	 * @param bookDTO Datos del libro a añadir.
	 * @return La información del libro añadido junto con el estado de la operación.
	 */
	@PostMapping("/library/add")
	public ResponseEntity<?> createNewBook(@Valid @RequestBody BookDTO bookDTO) {
		try {
			BookDTO savedBook = libraryService.createNewBook(bookDTO);
			return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
		} catch (BookAlreadyExistsException ex) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", ex.getMessage());
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/**
	 * Endpoint para eliminar un libro de la biblioteca.
	 * 
	 * @param iSBN El ISBN del libro que se va a eliminar.
	 * @return Una respuesta que indica si el libro fue eliminado correctamente.
	 * @throws BookNotFoundException Si el libro con el ISBN especificado no se encuentra en la biblioteca.
	 */
	@DeleteMapping("/library/delete")
	public ResponseEntity<?> deleteBook(@RequestParam(required = true) Long iSBN) {
		try {
			libraryService.deleteBook(iSBN);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} catch (BookNotFoundException ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", ex.getMessage());
			errorResponse.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		} catch (Exception ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "Error interno del servidor");
			errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	/**
	 * Endpoint para ordenar un libro por parte de un usuario.
	 * 
	 * @param iSBN El ISBN del libro que se va a ordenar.
	 * @return Una respuesta que contiene el libro actualizado después de ser ordenado.
	 * @throws BookNotFoundException Si el libro con el ISBN especificado no se encuentra en la biblioteca.
	 */
	@PutMapping("/user/order")
	public ResponseEntity<?> orderBook(@RequestParam(required = true) Long iSBN) {
		try {
			BookDTO updatedBook = userService.orderBook(iSBN);
			return ResponseEntity.ok(updatedBook);
		} catch (BookNotFoundException ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", ex.getMessage());
			errorResponse.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		} catch (Exception ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "Error interno del servidor");
			errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	/**
	 * Endpoint para devolver un libro por parte de un usuario.
	 * 
	 * @param iSBN El ISBN del libro que se va a devolver.
	 * @return Una respuesta que contiene el libro actualizado después de ser devuelto.
	 * @throws BookNotFoundException Si el libro con el ISBN especificado no se encuentra en la biblioteca.
	 */
	@PutMapping("/user/return")
	public ResponseEntity<?> returnBook(@RequestParam(required = true) Long iSBN) {
		try {
			BookDTO updatedBook = userService.returnBook(iSBN);
			return ResponseEntity.ok(updatedBook);
		} catch (BookNotFoundException ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", ex.getMessage());
			errorResponse.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		} catch (Exception ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "Error interno del servidor");
			errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	/**
	 * Endpoint para obtener libros disponibles según su disponibilidad.
	 * 
	 * @param available Indica si se deben devolver solo los libros disponibles (true) o todos los libros (false).
	 * @return Una respuesta que contiene una lista de libros disponibles o todos los libros, según el parámetro 'available'.
	 */
	@GetMapping("/user/books")
	public ResponseEntity<?> getBooksByAvailability() {
		try {
			List<BookDTO> books = userService.searchAvailableBooks(true);
			return ResponseEntity.ok(books);
		} catch (Exception ex) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "Error al obtener libros disponibles");
			errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}


	@ExceptionHandler(BookAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Map<String, Object>> handleBookAlreadyExistsException(BookAlreadyExistsException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", ex.getMessage());
		response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
