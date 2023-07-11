package ch.zli.m223.ksh20.user.repository;

import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.impl.AppUserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUserImpl, Long> {

    public default AppUser insertUser(String firstName, String lastName, String email, String password, String role){
        var appUser = new AppUserImpl(firstName, lastName, email, password, role);
        return save(appUser);
    }

    public Optional<AppUser> findByEmail(String email);

}
