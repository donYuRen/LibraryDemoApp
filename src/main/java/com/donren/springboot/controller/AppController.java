package com.donren.springboot.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.donren.springboot.model.Book;
import com.donren.springboot.model.User;
import com.donren.springboot.service.BookService;
import com.donren.springboot.service.UserService;

@Controller
public class AppController {
	public static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	@Autowired
	BookService bookService; //Service which will do all data retrieval/manipulation work

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		return "index";
	}
/*	@RequestMapping("/books")
	String books(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		System.out.println("request books");
		return "index";
	}*/
	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page, Map<String, Object> model) {
		List<User> users  = userService.findAllUsers();
		logger.info("users.size():" + users.size());
		List<Book> books = bookService.findAllBooks();
		model.put("users", users);
		model.put("books", books);
		logger.info(page);
		return page;
	}

}
