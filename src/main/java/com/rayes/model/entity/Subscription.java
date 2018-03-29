package com.rayes.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;
    @JoinColumn(name = "book_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Book book;
    @JoinColumn(name = "person_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Person person;

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
