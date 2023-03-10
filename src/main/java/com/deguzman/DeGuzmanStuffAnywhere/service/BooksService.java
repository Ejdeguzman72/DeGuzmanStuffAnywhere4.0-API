package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.BooksDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.BookSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.BooksAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.BooksAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.BooksListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByNameRequest;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateBookNameException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.BooksJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.Books;

@Service
public class BooksService {

	@Autowired
	private BooksJpaDao booksJpaDao;

	@Autowired
	private BooksDaoImpl booksDaoImpl;

	public BooksListResponse findAllBookInformation() {
		BooksListResponse response = new BooksListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Books> list = booksDaoImpl.findAllBooksInformation();

		response.setList(list);
		return response;
	}

	public ResponseEntity<Map<String, Object>> getAllBooksPagination(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {

			List<Books> shop = booksJpaDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<Books> pageBooks = null;

			if (title == null) {
				pageBooks = booksJpaDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("books", shop);
			response.put("currentPage", pageBooks.getNumber());
			response.put("totalItems", pageBooks.getTotalElements());
			response.put("totalPages", pageBooks.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public BooksListResponse findAllBooksByAuthor(SearchByNameRequest request) {
		BooksListResponse response = new BooksListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Books> list = booksDaoImpl.findAllBooksByAuthor(request.getName());

		response.setList(list);
		return response;
	}

	public BookSearchResponse findBookInfomrationById(SearchByIntRequest request)
			throws ResourceNotFoundException {
		BookSearchResponse response = new BookSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Books book = booksDaoImpl.findBooksInformationById(request.getId());
		
		response.setBook(book);
		return response;
	}

	public BookSearchResponse findBookInformationByName(SearchByNameRequest request) {
		BookSearchResponse response = new BookSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Books book = booksDaoImpl.findBookInformationByName(request.getName());
		
		response.setBook(book);
		return response;
	}

	public long getBookCount() {
		return booksDaoImpl.getBookCount();
	}

	public BooksAddUpdateResponse addBooksInformation(BooksAddUpdateRequest request) throws DuplicateBookNameException {
		BooksAddUpdateResponse response = new BooksAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Books book = null;
		int count = 0;

		count = booksDaoImpl.addBooksInformation(request);

		if (count > 0) {
			book.setAuthor(request.getAuthor());
			book.setDescr(request.getDescr());
			book.setTitle(request.getTitle());
			if (book != null) {
				response.setBook(book);
			}
		}

		return response;
	}

	public BooksAddUpdateResponse updateBooksInformation(BooksAddUpdateRequest request) {
		BooksAddUpdateResponse response = new BooksAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Books book = null;
		int count = 0;

		count = booksDaoImpl.updateBooksInformation(request.getBook_id(), request);
		if (count > 0) {
			if (book != null) {
				response.setBook(book);
			}
		}

		return response;
	}

	public BookSearchResponse deleteBookInformation(SearchByIntRequest request) throws ResourceNotFoundException {
		BookSearchResponse response = new BookSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Books book = booksDaoImpl.findBooksInformationById(request.getId());
		int count = 0;
		
		count = booksDaoImpl.deleteBookInformation(request.getId());
		
		if (count > 0) {
			response.setBook(book);
		}
		
		return response;
	}

	public DeleteAllResponse deleteAllBookInformation() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = booksDaoImpl.deleteAllBookInformation();

		response.setCount(count);

		return response;
	}
}
