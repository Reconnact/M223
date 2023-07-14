package ch.zli.m223.ksh20.user.init;

import ch.zli.m223.ksh20.user.repository.BookingRepository;
import ch.zli.m223.ksh20.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class ServerInitialisation implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.insertUser("Franz", "Kafka", "franzkafka@gmail.com", "password", "member");
        userRepository.insertUser("Friedrich", "Dürrenmatt", "fduerrenmatt@gmail.com", "password", "admin");
        userRepository.insertUser("Haruki", "Murakami", "harukiM@gmial.com", "password", "member");
        bookingRepository.insertBooking(LocalDate.of(2023, 6, 6), true, false, 1L);
        bookingRepository.insertBooking(LocalDate.of(2023, 4, 5), false, false, 2L);
        bookingRepository.insertBooking(LocalDate.of(2023, 12, 27), false, true, 3L);
    }
}
