package ch.zli.m223.ksh20.user.model;

public interface Booking {

    Long getId();
    String getDate();
    boolean isFullDay();
    boolean accepted();


}
