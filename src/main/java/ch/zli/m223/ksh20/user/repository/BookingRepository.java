package ch.zli.m223.ksh20.user.repository;

import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.model.impl.BookingImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<BookingImpl, Long> {
    public default Booking insertBooking(LocalDate date, boolean isFullDay, boolean accepted){
        var booking = new BookingImpl(date, isFullDay, accepted);
        return save(booking);
    }
}
