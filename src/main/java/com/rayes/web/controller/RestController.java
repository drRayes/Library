package com.rayes.web.controller;


import com.rayes.model.entity.Book;
import com.rayes.model.entity.Subscription;
import com.rayes.model.service.BookService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/book")
public class RestController {

    @Autowired
    SessionFactory sessionFactory;
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


}
