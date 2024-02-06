package com.hillogy.LibraryManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hillogy.LibraryManagement.controller.BookController;
import com.hillogy.LibraryManagement.dto.BookDTO;
import com.hillogy.LibraryManagement.exception.BookAlreadyExistsException;
import com.hillogy.LibraryManagement.exception.BookNotFoundException;
import com.hillogy.LibraryManagement.model.Book;
import com.hillogy.LibraryManagement.repository.BookRepository;
import com.hillogy.LibraryManagement.service.LibraryService;
import com.hillogy.LibraryManagement.service.UserService;

import org.mockito.junit.MockitoJUnitRunner;

/**
 * Clase de pruebas unitarias para el controlador BookController.
 * 
 * @author oscaralejandroflorez@gmail.com
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class BookControllerIntegrationTest {
	
	@Mock
    private LibraryService libraryService;

    @Mock
    private UserService userService;
    
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    /**
     * Prepara el entorno para las pruebas.
     */
    @SuppressWarnings("deprecation")
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Prueba para el método searchBooks del controlador BookController.
     * 
     * @throws BookNotFoundException si no se encuentra un libro.
     */
    @Test
    public void testSearchBooks() throws BookNotFoundException {
        List<BookDTO> books = new ArrayList<>();
        books.add(new BookDTO());
        when(libraryService.searchBooksByTitle("Title")).thenReturn(books);

        List<BookDTO> result = bookController.searchBooks("Title", null, null);
        assertEquals(1, result.size());
    }

    /**
     * Prueba para el método createNewBook del controlador BookController.
     */
    @Test
    public void testCreateNewBook() throws BookAlreadyExistsException {
        BookDTO bookDTO = new BookDTO();
        ResponseEntity<BookDTO> responseEntity = new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
        when(libraryService.createNewBook(any(BookDTO.class))).thenReturn(bookDTO);

        ResponseEntity<?> result = bookController.createNewBook(bookDTO);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    /**
     * Prueba para el método deleteBook del controlador BookController.
     * 
     * @throws BookNotFoundException si no se encuentra un libro.
     */
    @Test
    public void testDeleteBook() throws BookNotFoundException {
        ResponseEntity<Map<String, Boolean>> expectedResponse = ResponseEntity.ok(Collections.singletonMap("deleted", true));
        doNothing().when(libraryService).deleteBook(anyLong());

        ResponseEntity<?> result = bookController.deleteBook(123L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    /**
     * Prueba para el método orderBook del controlador BookController.
     * 
     * @throws BookNotFoundException si no se encuentra un libro.
     */
    @Test
    public void testOrderBook() throws BookNotFoundException {
        BookDTO bookDTO = new BookDTO();
        ResponseEntity<BookDTO> responseEntity = new ResponseEntity<>(bookDTO, HttpStatus.OK);
        when(userService.orderBook(anyLong())).thenReturn(bookDTO);

        ResponseEntity<?> result = bookController.orderBook(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    /**
     * Prueba para el método returnBook del controlador BookController.
     * 
     * @throws BookNotFoundException si no se encuentra un libro.
     */
    @Test
    public void testReturnBook() throws BookNotFoundException {
        BookDTO bookDTO = new BookDTO();
        ResponseEntity<BookDTO> responseEntity = new ResponseEntity<>(bookDTO, HttpStatus.OK);
        when(userService.returnBook(anyLong())).thenReturn(bookDTO);

        ResponseEntity<?> result = bookController.returnBook(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    /**
     * Prueba para el método getBooksByAvailability del controlador BookController.
     */
    @Test
    public void testGetBooksByAvailability() {
        List<BookDTO> books = new ArrayList<>();
        when(userService.searchAvailableBooks(true)).thenReturn(books);

        ResponseEntity<?> responseEntity = bookController.getBooksByAvailability();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}