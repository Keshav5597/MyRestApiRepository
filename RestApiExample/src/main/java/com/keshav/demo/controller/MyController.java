package com.keshav.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keshav.demo.model.Book;
import com.keshav.demo.repository.BookRepository;

@RestController
public class MyController {
	@Autowired
	private BookRepository bookRepository;
	@PostMapping("/addBook")
		public String addBook(@RequestBody Book book)
		{
			bookRepository.save(book);
			return "book is added successfully";
		}
	
		@GetMapping("/findAllBooks")
		public List<Book>findAllBooks()
		{
			return bookRepository.findAll();
		}
		
		@GetMapping("/findBook/{bookId}")
		public Book findBook(@PathVariable Integer bookId)
		{
			Book book=new Book();
			Optional<Book>o=bookRepository.findById(bookId);
			if(!o.isEmpty())
			{
				book=o.get();
			}
			return book;
		}
		@DeleteMapping("/deleteBook")
		public String deleteBook(@RequestParam Integer bookId)
		{
			String msg="Book not found";
			Book book=findBook(bookId);
			if(book.getBookId()!=null)
			{
				bookRepository.delete(book);
				msg= "book is deleted.....";
			}
			return msg;
		}
		
		@PutMapping("/updateBook")
		public Book updateBook(@RequestBody Book book)
		{
			Book myBook=findBook(book.getBookId());
			if(myBook.getBookId()==null)
			{
				return myBook;
			}
			return bookRepository.save(book);
		}
}
