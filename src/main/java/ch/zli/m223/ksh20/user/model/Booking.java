package ch.zli.m223.ksh20.user.model;

import java.time.LocalDate;

public interface Booking {

    Long getId();
    LocalDate getDate();
    boolean isFullDay();
    boolean accepted();
    Long getUserId();


}
