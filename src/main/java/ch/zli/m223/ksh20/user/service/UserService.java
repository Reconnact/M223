package ch.zli.m223.ksh20.user.service;

import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.impl.AppUserImpl;

import java.util.List;

public interface UserService {
    List<AppUser> getUserList();

    AppUserImpl getUserById(Long id);

    AppUser addUser(String firstName, String lastName,
                          String email, String password);

    AppUser login(String email, String password);
    void deleteUser(Long id);
}
