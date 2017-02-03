package com.donren.springboot.service;

import java.util.List;

import com.donren.springboot.model.Book;

public interface BookService {
	Book findById(Long id);

	Book findByName(String name);

	void saveBook(Book book);

	void updateBook(Book book);

	void deleteBookById(Long id);

	void deleteAllBooks();

	List<Book> findAllBooks();
	List<Book> findBooksByStatus(boolean status);
	List<Book> findBooksByUserId(Long userId);

	boolean isBookExist(Book book);
}
