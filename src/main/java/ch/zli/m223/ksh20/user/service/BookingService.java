package ch.zli.m223.ksh20.user.service;

import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.model.impl.BookingImpl;
import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();
    BookingImpl getBookingById(Long id);
    Booking addBooking(String date, boolean isFullDay, boolean accepted);
    void deleteBooking(Long id);

}
