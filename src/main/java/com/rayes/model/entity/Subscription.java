package com.rayes.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    @Column(name = "date_of_issue")
    private Date issueDate;
    @Column(name = "return_date")
    private Date returnDate;

    public Subscription() {}

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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
