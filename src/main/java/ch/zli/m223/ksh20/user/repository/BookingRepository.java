package ch.zli.m223.ksh20.user.repository;

import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.model.impl.AppUserImpl;
import ch.zli.m223.ksh20.user.model.impl.BookingImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingImpl, Long> {
    public default Booking insertBooking(String date, boolean isFullDay, boolean accepted){
        var booking = new BookingImpl(date, isFullDay, accepted);
        return save(booking);
    }

    public default Booking updateBooking(BookingImpl booking){
        return save(booking);
    }
}
