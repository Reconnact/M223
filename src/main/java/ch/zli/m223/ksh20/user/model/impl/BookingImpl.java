package ch.zli.m223.ksh20.user.model.impl;

import ch.zli.m223.ksh20.user.model.Booking;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity(name = "Bookings")
public class BookingImpl implements Booking {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date")
    private String date;

    @Column(name = "isFullDay")
    private boolean isFullDay;

    @Column(name = "accepted")
    private boolean accepted;

    // TODO: Author

    public BookingImpl(String date, boolean isFullDay, boolean accepted){
        this.date = date;
        this.isFullDay = isFullDay;
        this.accepted = accepted;
    }

    public BookingImpl() {

    }

    public Long getId() {
        return id;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public boolean isFullDay() {
        return isFullDay;
    }

    @Override
    public boolean accepted() {
        return accepted;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFullDay(boolean fullDay) {
        isFullDay = fullDay;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
