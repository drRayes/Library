package com.rayes.model.service;

import com.rayes.model.entity.Book;
import com.rayes.model.entity.Person;
import com.rayes.model.entity.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    SessionFactory sessionFactory;

    public List<Book> searchBook(String string)throws RuntimeException {
        string = string.toLowerCase();
        List<Book> bookList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Book");
            bookList = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Book> filterBooks = new ArrayList<>();

        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getName().toLowerCase().contains(string)
                    || bookList.get(i).getAuthor().toLowerCase().contains(string)) {
                filterBooks.add(bookList.get(i));
            }

        }
        return filterBooks;
    }

    public List<Subscription> listSubscription(String login)throws RuntimeException {
        List<Subscription> bookList = new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Person WHERE login='" + login + "'");
            Person person = (Person) query.getSingleResult();
            query = session.createQuery("FROM Subscription WHERE person=" + person.getPersonId());
            bookList = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    public Book getBook(Long id) {
        Book book = new Book();
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("From Book " + "WHERE id = " + id);
            book = (Book) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return book;
    }
}


