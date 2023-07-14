package ch.zli.m223.ksh20.user.controller.rest;

import ch.zli.m223.ksh20.user.controller.rest.dto.BookingDto;
import ch.zli.m223.ksh20.user.controller.rest.dto.BookingInputDto;
import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.security.JwtUtils;
import ch.zli.m223.ksh20.user.service.BookingService;
import ch.zli.m223.ksh20.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/list")
    List<BookingDto> getBookingList(@RequestHeader("Authorization") String header){
        String token = header.split(" ")[0].trim();
        if (jwtUtils.getRoleFromJwtToken(token).equals("admin")){
            return bookingService.getBookingList().stream()
                    .map(booking -> new BookingDto(booking, userService))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @GetMapping("/own/list")
    List<BookingDto> getOwnList(
            @RequestHeader("Authorization") String header
    ){
        String token = header.split(" ")[0].trim();
        return bookingService.getOwnBookings(jwtUtils.getIdFromJwtToken(token)).stream()
                .map(booking -> new BookingDto(booking, userService))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    Map<String, String> getBooking(@PathVariable Long id,
                                   @RequestHeader("Authorization") String header){
        String token = header.split(" ")[0].trim();
        HashMap<String, String> map = new HashMap<>();
        if (jwtUtils.getRoleFromJwtToken(token).equals("admin")){
            Booking booking = bookingService.getBookingById(id);
            AppUser user = userService.getUserById(booking.getUserId());
            map.put("date", booking.getDate().toString());
            map.put("isFullDay", String.valueOf(booking.isFullDay()));
            map.put("accepted", String.valueOf(booking.accepted()));
            map.put("user", user.getFirstName() + " " + user.getLastName());
        }
        return map;
    }

    @PutMapping("/{id}/update")
    void update(@RequestBody BookingInputDto bookingInput,
                @PathVariable Long id,
                @RequestHeader("Authorization") String header) {
        String token = header.split(" ")[0].trim();
        if (jwtUtils.getRoleFromJwtToken(token).equals("admin")){
            bookingService.updateBooking(id, LocalDate.parse(bookingInput.date), bookingInput.isFullDay, bookingInput.accepted);
        }else {
            System.out.println("not allowed");
        }
    }

    @PostMapping("/add")
    void addBooking(@RequestBody BookingInputDto bookingInput,
                    @RequestHeader("Authorization") String header){
        String token = header.split(" ")[0].trim();
        if (jwtUtils.getRoleFromJwtToken(token) != null || jwtUtils.getRoleFromJwtToken(token) != ""){
            bookingService.addBooking(LocalDate.parse(bookingInput.date), bookingInput.isFullDay, false,
                    jwtUtils.getIdFromJwtToken(token));
        } else {
            System.out.println("log in");
        }
    }

    @DeleteMapping("/{id}/delete")
    void deleteBooking(Model model,
                       @PathVariable Long id,
                       @RequestHeader("Authorization") String header){
            bookingService.deleteBooking(id);
            List<Booking> bookings = bookingService.getBookingList();
            model.addAttribute("users", bookings);

    }

}
