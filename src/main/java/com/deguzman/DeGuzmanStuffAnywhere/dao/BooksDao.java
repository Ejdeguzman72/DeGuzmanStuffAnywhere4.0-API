package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateBookNameException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Books;

public interface BooksDao {

	public List<Books> findAllBooksInformation();

	public List<Books> findAllBooksByAuthor(String author);

	public Books findBooksInformationById(int book_id) throws ResourceNotFoundException;

	public Books findBookInformationByName(String title);

	public long getBookCount();

	public int addBooksInformation(Books book) throws DuplicateBookNameException;

	public int updateBooksInformation(int book_id, @RequestBody Books book);

	public int deleteBookInformation(int book_id);

	public int deleteAllBookInformation();

}
