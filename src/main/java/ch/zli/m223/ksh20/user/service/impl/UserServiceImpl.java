package ch.zli.m223.ksh20.user.service.impl;

import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.impl.AppUserImpl;
import ch.zli.m223.ksh20.user.repository.UserRepository;
import ch.zli.m223.ksh20.user.service.UserService;
import ch.zli.m223.ksh20.user.service.exception.InvalidEmailOrPasswordException;
import ch.zli.m223.ksh20.user.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AppUser> getUserList() {
       return new ArrayList<>(userRepository.findAll());
    }



    @Override
    public AppUserImpl getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public AppUser addUser(String firstName, String lastName,
                                 String email, String password) {
        String role = "member";
        if (password == null || password == "" || email == null || email == "" ) {
            throw new InvalidEmailOrPasswordException();
        }

        if (userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException();
        }

        if (getUserList().isEmpty()){
            role = "admin";
        }

        return userRepository.insertUser(firstName, lastName, email, password, role);

    }

    @Override
    public AppUser login(String email, String password) {
        Optional<AppUser> u = userRepository.findByEmail(email);
        if (u.isPresent() && u.get().getPassword().equals(password)) {
            return u.get();
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    public void updateUser(Long id, String firstName, String lastName, String email) {
        AppUserImpl user = getUserById(id);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        userRepository.updateUser(user);
    }


}
