package com.donren.springboot.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.donren.springboot.model.Book;
import com.donren.springboot.model.User;

/*@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})*/
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.ANY)
@TestPropertySource(locations="classpath:application-test.yml")
@ActiveProfiles("test")
public class BookRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    private List<Book> bookList = new ArrayList<Book>();


	@Test
	public void test() {
		User user = createUser("user1", 40, "address1", "F", null);
		User user2 = createUser("user2", 42, "address2", "M", null);
		
		
		User targetUser = entityManager.persistFlushFind(user);
		User targetUser2 = entityManager.persistFlushFind(user2);

		Book book1 = createBook("author1", "isbn1", "book1", true, null);
		Book book2 = createBook("author2", "isbn2", "book2", true, null);
		Book book3 = createBook("author1", "isbn3", "book3", true, null);

		Book tgBook1 = entityManager.persist(book1);
		Book tgBook2 = entityManager.persist(book2);
		Book tgBook3 = entityManager.persist(book3);
		System.out.println("book3:" + tgBook3);

		tgBook1.setUser(targetUser);
		tgBook3.setUser(targetUser);
		bookList.add(tgBook1);
		bookList.add(tgBook3);
		bookRepository.save(tgBook1);
		bookRepository.save(tgBook3);

		targetUser.setBooks(bookList);
		userRepository.save(targetUser);
		
		System.out.println("targerUser:" + targetUser);

		List<Book> books = bookRepository.findAll();
		assertEquals(5, books.size());
		System.out.println("books:" + books);
		List<User> users = userRepository.findAll();
		System.out.println("users:" + users);
		List<Book> bkm = userRepository.findOne(1L).getBooks();
		assertEquals(2, bkm.size());
		
		List<Book> books2 = bookRepository.findByLender(targetUser.getId());
		List<Book> books3 = bookRepository.findByLender(user2.getId());
		assertEquals(4, users.size());
		assertEquals(2, books2.size());
		assertEquals(0, books3.size());
		System.out.println("return book control");
		User ru = userRepository.getOne(user.getId());
		List<Book> lentBooks = ru.getBooks();
		ru.setBooks(null);
		for (Book bk : lentBooks) {
			bk.setUser(null);
			bookRepository.save(bk);
			System.out.println(bk);
		}
		userRepository.save(ru);
		System.out.println("after flush:" + userRepository.findOne(user.getId()).getBooks());
		bkm = bookRepository.findByLender(1L);
		assertEquals(2, bkm.size());
		
	}

	private User createUser(String name, Integer age, String address, String sex, List<Book> books) {
		User user = new User();
		user.setAddress(address);
//		user.setId(id);
		user.setName(name);
		user.setSex(sex);
		user.setBooks(books);
		user.setAge(age);
		
		return user;
	}

	private Book createBook(String author, String isbn, String name, boolean status, User user) {
		Book book =new Book();
		book.setAuthor(author);
		book.setIsbn(isbn);
		book.setName(name);
		book.setStatus(status);
		book.setUser(user);
		return book;
	}
}
