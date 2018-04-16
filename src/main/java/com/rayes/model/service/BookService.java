package com.rayes.model.service;

import com.rayes.model.entity.Book;
import com.rayes.model.entity.Person;
import com.rayes.model.entity.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class for saving updating or editing book or subscription entity in database.
 *
 * @author rayes
 */
@Service
public class BookService {
    @Autowired
    SessionFactory sessionFactory;

    /**
     * Method for searching book by name or author.
     * @param string
     * @return
     * @throws RuntimeException
     */
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

    /**
     * Method for fetch list subscription by login.
     * @param login
     * @return
     * @throws RuntimeException
     */
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

    //method for fetch book by id (temporary method)
    public Book getBook(Long id) {
        Book book = new Book();
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Book WHERE id = " + id);
            book = (Book) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    /**
     * Method for saving book in database.
     * @param book
     * @return
     */
    @Transactional
    public Book saveBook(Book book) {
        try(Session session = sessionFactory.openSession()) {
            session.save(book);
            book = (Book) session.createQuery("FROM Book WHERE name='"
                    + book.getName() + "'").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    /**
     * Method for saving subscription in database.
     * @param subscription
     * @return
     */
    @Transactional
    public Subscription saveSubscription(Subscription subscription) {
        try(Session session = sessionFactory.openSession()) {
            Book book = (Book) session.createQuery("FROM Book WHERE name='" +
                    subscription.getBook().getName() + "'").getSingleResult();
            subscription.setBook(book);
            Person person = (Person) session.createQuery("FROM Person WHERE login='" +
                    subscription.getPerson().getLogin() + "'").getSingleResult();
            subscription.setPerson(person);
            subscription.setIssueDate(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 1);
            subscription.setReturnDate(calendar.getTime());
            session.save(subscription);
            subscription = (Subscription) session.createQuery("FROM Subscription WHERE " +
                    "person=" + person.getPersonId() + "AND book=" + book.getBookId()).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return subscription;
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            list = session.createQuery("FROM Book").getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}


