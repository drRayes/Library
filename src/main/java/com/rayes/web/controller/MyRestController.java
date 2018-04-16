package com.rayes.web.controller;


import com.rayes.model.entity.Book;
import com.rayes.model.entity.Person;
import com.rayes.model.entity.Subscription;
import com.rayes.model.service.BookService;
import com.rayes.model.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class MyRestController {

    @Autowired
    PersonService personService;
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBook(@PathVariable("id") Long id)throws Exception {
        return bookService.getBook(id);

    }

    @RequestMapping(value = "/list/{login}", method = RequestMethod.GET)
    @ResponseBody
    public List<Subscription> getBookList(@PathVariable("login") String login) throws Exception {
        return bookService.listSubscription(login);
    }

    @RequestMapping(value = "/search/{string}", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> searchBook(@PathVariable("string") String string) {
        return bookService.searchBook(string);
    }

    @PostMapping(value = "/addCustomer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Person addCustomer(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @PostMapping(value = "/addBook", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping(value = "/addSubscription", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Subscription addSubscription(@RequestBody Subscription subscription) {
        return bookService.saveSubscription(subscription);
    }

    @RequestMapping(value = "/allBooks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


}
