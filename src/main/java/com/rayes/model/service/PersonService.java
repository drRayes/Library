package com.rayes.model.service;

import com.rayes.model.entity.Person;
import com.rayes.model.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class for saving, deleting or editing persons entity in database.
 *
 * @author rayes
 */
@Service
public class PersonService {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Method for saving person on database
     * @param person
     * @return
     */
    @Transactional
    public Person savePerson(Person person) {
        Role role = new Role();
        role.setLogin(person.getLogin());
        role.setRole("customer");
        person.setStatus(true);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        try(Session session = sessionFactory.openSession()) {
            session.save(person);
            session.save(role);
            person = (Person) session.createQuery("FROM Person WHERE login ='" + person.getLogin() + "'").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    /**
     * Method for fetch person from database by login.
     * @param login
     * @return
     */
    public Person getPersonByLogin(String login) {
        Person person = null;
        try(Session session = sessionFactory.openSession()) {
            person = (Person) session.createQuery("FROM Person WHERE login='" + login + "'").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    @Transactional
    public String updatePerson(Person person) {
        Person personId;
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Person " + person.getLogin() + " was updated!";
    }

    public String adminCreatePerson(Person person, Role role) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        try(Session session = sessionFactory.openSession()) {
            session.save(person);
            session.save(role);
            return "Person with login " + person.getLogin() + " and role " +
                    role.getRole() + " was created!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Person> getListOfPersons() {
        List<Person> list;
        try(Session session = sessionFactory.openSession()) {
            list = session.createQuery("FROM Person").getResultList();
        }
        return list;
    }
}
