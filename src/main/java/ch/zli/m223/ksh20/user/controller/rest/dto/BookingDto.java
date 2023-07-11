package ch.zli.m223.ksh20.user.controller.rest.dto;

import ch.zli.m223.ksh20.user.model.Booking;

public class BookingDto {

    public String date;
    public boolean isFullDay, accepted;
    public Long id;
    public BookingDto(Booking booking) {
        id = booking.getId();
        date = booking.getDate();
        isFullDay = booking.isFullDay();
        accepted = booking.accepted();
    }
}
