package ch.zli.m223.ksh20.user.model.impl;

import ch.zli.m223.ksh20.user.model.Bookings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity(name = "Bookings")
public class BookingsImpl implements Bookings {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "isFullDay")
    private boolean isFullDay;

    @Column(name = "accepted")
    private boolean accepted;


    public Long getId() {
        return id;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public boolean isFullDay() {
        return false;
    }

    @Override
    public boolean accepted() {
        return false;
    }
}
