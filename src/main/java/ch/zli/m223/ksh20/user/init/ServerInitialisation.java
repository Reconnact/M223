package ch.zli.m223.ksh20.user.init;

import ch.zli.m223.ksh20.user.repository.BookingRepository;
import ch.zli.m223.ksh20.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerInitialisation implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.insertUser("Hermann", "Witte", "email", "password", "admin");
        userRepository.insertUser("Jakub", "Corona", "email1", "password", "admin");

        bookingRepository.insertBooking("heute", true, false);
    }
}
