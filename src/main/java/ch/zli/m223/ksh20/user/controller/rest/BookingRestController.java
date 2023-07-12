package ch.zli.m223.ksh20.user.controller.rest;

import ch.zli.m223.ksh20.user.controller.rest.dto.BookingDto;
import ch.zli.m223.ksh20.user.controller.rest.dto.BookingInputDto;
import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    Booking getBooking(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}/update")
    void update(@RequestBody BookingInputDto bookingInput, @PathVariable Long id) {
        bookingService.updateBooking(id, bookingInput.date, bookingInput.isFullDay, bookingInput.accepted);
    }



}
