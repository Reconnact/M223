package ch.zli.m223.ksh20.user.repository;

import ch.zli.m223.ksh20.user.model.Bookings;
import ch.zli.m223.ksh20.user.model.impl.BookingsImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<BookingsImpl, Long> {
    public default Bookings insertBooking(LocalDate date, boolean isFullDay, boolean accepted){
        var booking = new BookingsImpl(date, isFullDay, accepted);
        return save(booking);
    }
}
