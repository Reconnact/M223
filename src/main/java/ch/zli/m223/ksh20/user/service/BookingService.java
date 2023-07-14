package ch.zli.m223.ksh20.user.service;

import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.model.impl.BookingImpl;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    List<Booking> getBookingList();
    List<Booking> getOwnBookings(Long userId);
    BookingImpl getBookingById(Long id);
    Booking addBooking(LocalDate date, boolean isFullDay, boolean accepted, Long userId);
    void updateBooking(Long id, LocalDate date, boolean isFullDay, boolean accepted);
    void deleteBooking(Long id);

}
