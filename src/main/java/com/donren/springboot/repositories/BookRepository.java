package com.donren.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.donren.springboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByName(String name);
	List<Book> findByAuthor(String author);
	List<Book> findByStatus(boolean status);
	@Query("SELECT b FROM Book b WHERE b.user.id = :userId")
	List<Book> findByLender(@Param("userId") Long userId);
}
