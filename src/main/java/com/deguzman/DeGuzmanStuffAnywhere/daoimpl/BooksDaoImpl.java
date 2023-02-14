package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.dao.BooksDao;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateBookNameException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Books;

@Repository
public class BooksDaoImpl implements BooksDao {

	String GET_ALL_BOOKS = "SELECT * FROM BOOKS ORDER BY Title";
	String GET_BOOK_INFORMATION_BY_ID = "SELECT * FROM BOOKS WHERE BOOK_ID = ?";
	String GET_BOOK_INFORMATION_BY_AUTHOR = "SELECT * FROM BOOKS WHERE AUTHOR = ?";
	String GET_BOOK_INFORMATION_NAME = "SELECT * FROM BOOKS WHERE Title = ?";
	String GET_BOOK_COUNT = "SELECT COUNT(*) FROM BOOKS";
	String ADD_BOOK_INFORMATION = "INSERT INTO BOOKS " + "(AUTHOR, DESCR, TITLE) " + "VALUES(?, ?, ?)";
	String UPDATE_BOOK_INFORMATION = "UPDATE books " + "SET author=?, descr=?, title=?" + "WHERE book_id=?";
	String DELETE_BOOK_INFORMATION_BY_ID = "DELETE FROM BOOKS WHERE BOOK_ID = ?";
	String DELETE_ALL_BOOK_INFORMATION = "DELETE FROM BOOKS";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(BooksDaoImpl.class);

	@Override
	@Cacheable(value = "bookList")
	public List<Books> findAllBooksInformation() {
		List<Books> booksList = new ArrayList<>();
		try {
			booksList = jdbcTemplate.query(GET_ALL_BOOKS, BeanPropertyRowMapper.newInstance(Books.class));
			
			LOGGER.info("Retrieving all books...");			
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}

		return booksList;
	}

	@Override
	public List<Books> findAllBooksByAuthor(String author) {
		List<Books> booksListAuthor = new ArrayList<>(); 
		try {
			booksListAuthor = jdbcTemplate.query(GET_BOOK_INFORMATION_BY_AUTHOR,
					(rs, rowNum) -> new Books(rs.getInt("BOOK_ID"), rs.getString("NAME"), rs.getString("DESCR"),
							rs.getString("AUTHOR")),
					author);
			
			LOGGER.info("Retriving all books by Author: " + author);			
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}

		return booksListAuthor;
	}

	@Override
	@Cacheable(value = "bookById", key = "#book_id")
	public ResponseEntity<Books> findBooksInformationById(int book_id) throws ResourceNotFoundException {
		Books book = new Books();
		try {
			book = jdbcTemplate.queryForObject(GET_BOOK_INFORMATION_BY_ID,
					BeanPropertyRowMapper.newInstance(Books.class), book_id);
			
			LOGGER.info("Retrieved Book Information By ID: " + book_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return ResponseEntity.ok().body(book);
	}

	@Override
	@Cacheable(value = "bookName", key = "#name")
	public ResponseEntity<Books> findBookInformationByName(String name) {
		Books book = new Books();
		try {
			book = jdbcTemplate.queryForObject(GET_BOOK_INFORMATION_NAME,
					BeanPropertyRowMapper.newInstance(Books.class), name);
			
			LOGGER.info("Retrived Book Information: " + " " + book.getTitle() + " " + book.getAuthor());			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error: " + e.toString());
		}

		return ResponseEntity.ok().body(book);
	}

	@Override
	public long getBookCount() {
		long count = jdbcTemplate.queryForObject(GET_BOOK_COUNT, Integer.class);

		LOGGER.info("Getting count of all books...");

		return count;
	}

	@Override
	@CachePut(value = "bookList")
	public int addBooksInformation(Books book) throws DuplicateBookNameException {
		int result = 0;
		try {
			String author = book.getAuthor();
			String descr = book.getDescr();
			String name = book.getTitle();
			
			if (checkBookNames(book.getTitle())) {
				throw new DuplicateBookNameException("Book Already Exists");
			}

			LOGGER.info("Adding book information: " + name + " " + author);
			
			result = jdbcTemplate.update(ADD_BOOK_INFORMATION, new Object[] { author, descr, name });
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		

		return result;
	}

	@Override
	@CachePut(value = "bookById", key = "#book_id")
	public int updateBooksInformation(int book_id, Books book) {

		int result = 0;
		Books updatedBook = new Books();
		try {
			updatedBook = jdbcTemplate.queryForObject(GET_BOOK_INFORMATION_BY_ID,
					BeanPropertyRowMapper.newInstance(Books.class), book_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		try {
			if (updatedBook != null) {
				updatedBook.setAuthor(book.getAuthor());
				updatedBook.setDescr(book.getDescr());
				updatedBook.setTitle(book.getTitle());
				updatedBook.setBook_id(book_id);
				
				result = jdbcTemplate.update(UPDATE_BOOK_INFORMATION, new Object[] { updatedBook.getAuthor(),
						updatedBook.getDescr(), updatedBook.getTitle(), updatedBook.getBook_id() });
				
				LOGGER.info("Updating book information with book_id: " + book_id);
			
			}
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}

		return result;
	}

	@Override
	@CachePut(value = "bookById", key = "#book_id")
	public int deleteBookInformation(int book_id) {
		int count = 0;
		try {
			count = jdbcTemplate.update(DELETE_BOOK_INFORMATION_BY_ID, book_id);
			LOGGER.info("Deleting Book With ID: " + book_id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return count;
	}

	@Override
	@CachePut(value = "bookList")
	public int deleteAllBookInformation() {
		int count = 0;
		
		try {
			count = jdbcTemplate.update(DELETE_ALL_BOOK_INFORMATION);

			LOGGER.info("Deleting all books...");
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return count;
	}

	public boolean checkBookNames(String name) {

		List<Books> bookList = findAllBooksInformation();
		List<String> namesList;
		boolean result = false;

		namesList = bookList.stream().map(Books::getTitle).collect(Collectors.toList());

		if (namesList.contains(name)) {
			result = true;
		}

		return result;
	}

}
