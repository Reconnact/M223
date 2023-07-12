package ch.zli.m223.ksh20.user.controller.rest;

import ch.zli.m223.ksh20.user.controller.rest.dto.BookingDto;
import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/list")
    Booking getBookingList(){
        return bookingService.getBookingList().get(0);
        /*return bookingService.getAllBookings().stream()
                .map(booking -> new BookingDto(booking))
                .collect(Collectors.toList());*/
    }



}
