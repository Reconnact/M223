package ch.zli.m223.ksh20.user.service.impl;

import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.model.impl.BookingImpl;
import ch.zli.m223.ksh20.user.repository.BookingRepository;
import ch.zli.m223.ksh20.user.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingRepository.findAll());
    }

    @Override
    public BookingImpl getBookingById(Long id) {
        return bookingRepository.getReferenceById(id);
    }

    @Override
    public Booking addBooking(LocalDate date, boolean isFullDay, boolean accepted) {
        // TODO: validation
        return bookingRepository.insertBooking(date, isFullDay, accepted);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.delete(getBookingById(id));
    }
}