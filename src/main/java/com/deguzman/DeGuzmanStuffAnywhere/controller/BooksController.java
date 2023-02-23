package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.domain.BookSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.BooksAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.BooksAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.BooksListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByNameRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UriConstants;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateBookNameException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.service.BooksService;

import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BooksController {
	
	@Autowired
	private BooksService bookService;

	@GetMapping(value = UriConstants.GET_ALL_BOOKS)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public BooksListResponse getAllBooksInformation() {
		BooksListResponse response = bookService.findAllBookInformation();
		return response;
	}

	@GetMapping(value = UriConstants.GET_ALL_BOOKS_PAGINATION)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<Map<String, Object>> getAllBooksPagination(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return bookService.getAllBooksPagination(name, page, size);
	}

	@GetMapping(value = UriConstants.GET_BOOKS_BY_AUTHOR)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public BooksListResponse getBooksInformationByAuthor(@RequestBody @Valid SearchByNameRequest request) {
		BooksListResponse response = bookService.findAllBooksByAuthor(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_BOOK_BY_ID)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public BookSearchResponse getBookInformationById(@RequestBody @Valid SearchByIntRequest request) throws ResourceNotFoundException {
		BookSearchResponse response = bookService.findBookInfomrationById(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_BOOK_BY_NAME)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public BookSearchResponse getBookInformationByName(@RequestBody @Valid SearchByNameRequest request) {
		BookSearchResponse response = bookService.findBookInformationByName(request);
		return response;
	}

	@PostMapping(value = UriConstants.ADD_BOOK_INFORMATION)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public BooksAddUpdateResponse addBookInformation(@RequestBody @Valid BooksAddUpdateRequest request) throws DuplicateBookNameException {
		BooksAddUpdateResponse response = bookService.addBooksInformation(request);
		return response;
	}
	
	@PutMapping(value = UriConstants.UPDATE_BOOK_INFORMATION)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public BooksAddUpdateResponse updateBookInformation(@RequestBody @Valid BooksAddUpdateRequest request) {
		BooksAddUpdateResponse response = bookService.updateBooksInformation(request);
		return response;
	}

	@DeleteMapping(value = UriConstants.DELETE_BOOK)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public BookSearchResponse deleteBookInformationById(@RequestBody @Valid SearchByIntRequest request) throws ResourceNotFoundException {
		BookSearchResponse response = bookService.deleteBookInformation(request);
		return response;
	}

	@DeleteMapping(value = UriConstants.DELETE_ALL_BOOKS)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public DeleteAllResponse deleteAllBookInformation() {
		DeleteAllResponse response = bookService.deleteAllBookInformation();
		return response;
	}
}
