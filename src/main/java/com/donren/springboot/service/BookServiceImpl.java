package com.donren.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donren.springboot.model.Book;
import com.donren.springboot.model.User;
import com.donren.springboot.repositories.BookRepository;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Override
	public Book findById(Long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public Book findByName(String name) {
		return bookRepository.findByName(name);
	}

	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void updateBook(Book book) {
		saveBook(book);
		
	}

	@Override
	public void deleteBookById(Long id) {
		bookRepository.delete(id);
		
	}

	@Override
	public void deleteAllBooks() {
		bookRepository.deleteAll();
		
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findBooksByStatus(boolean status) {
		// TODO Auto-generated method stub
		return bookRepository.findByStatus(status);
	}

	@Override
	public boolean isBookExist(Book book) {
		return findByName(book.getName()) != null;
	}

	@Override
	public List<Book> findBooksByUserId(Long userId) {
		return bookRepository.findByLender(userId);
	}
}
