package ch.zli.m223.ksh20.user.controller.rest;
import ch.zli.m223.ksh20.user.controller.rest.dto.UserDto;
import ch.zli.m223.ksh20.user.controller.rest.dto.UserInputDto;
import ch.zli.m223.ksh20.user.controller.rest.dto.UserLoginDto;
import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.impl.AppUserImpl;
import ch.zli.m223.ksh20.user.service.UserService;
import ch.zli.m223.ksh20.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    void register(@RequestBody UserInputDto userInput) {
        userService.addUser(userInput.firstName, userInput.lastName, userInput.email, userInput.password);

    }

    @PostMapping("/login")
    void login(@RequestBody UserLoginDto userInput) {

        System.out.println(userService.login(userInput.email, userInput.password));
    }

    @GetMapping("/{id}")
    Map<String, String> getUser(@PathVariable Long id) {
        AppUser user = userService.getUserById(id);
        HashMap<String, String> map = new HashMap<>();
        if (user.getEmail() == "" || user.getEmail().isEmpty()){
            return map;
        }
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("email", user.getEmail());
        return map;
    }

    @PutMapping("/{id}/update")
    void update(@RequestBody UserInputDto userInput, @PathVariable Long id) {
        userService.updateUser(id, userInput.firstName, userInput.lastName, userInput.email);
    }

    @DeleteMapping("/{id}/delete")
    void delete(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        List<AppUser> users = userService.getUserList();
        model.addAttribute("users", users);
    }

    @GetMapping("/list")
    List<UserDto> getUserList() {
       return userService.getUserList().stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
    }
}
