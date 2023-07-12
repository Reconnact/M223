package ch.zli.m223.ksh20.user.controller.rest.dto;

import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.service.UserService;
import ch.zli.m223.ksh20.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class BookingDto {

    public LocalDate date;
    public boolean isFullDay, accepted;
    public Long id, userId;

    public BookingDto(Booking booking) {

        id = booking.getId();
        date = booking.getDate();
        isFullDay = booking.isFullDay();
        accepted = booking.accepted();
        userId = booking.getUserId();
    }
}
