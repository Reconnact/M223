package ch.zli.m223.ksh20.user.controller.rest;

import ch.zli.m223.ksh20.user.controller.rest.dto.BookingDto;
import ch.zli.m223.ksh20.user.controller.rest.dto.BookingInputDto;
import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.model.impl.BookingImpl;
import ch.zli.m223.ksh20.user.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/list")
    List<BookingDto> getBookingList(){
        return bookingService.getBookingList().stream()
                .map(booking -> new BookingDto(booking))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    Map<String, String> getBooking(@PathVariable Long id){
        Booking booking = bookingService.getBookingById(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("date", booking.getDate().toString());
        map.put("isFullDay", String.valueOf(booking.isFullDay()));
        map.put("accepted", String.valueOf(booking.accepted()));
        return map;
    }

    @PutMapping("/{id}/update")
    void update(@RequestBody BookingInputDto bookingInput, @PathVariable Long id) {
        bookingService.updateBooking(id, LocalDate.parse(bookingInput.date), bookingInput.isFullDay, bookingInput.accepted);
    }



}
