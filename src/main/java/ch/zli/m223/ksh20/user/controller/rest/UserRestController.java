package ch.zli.m223.ksh20.user.controller.rest;
import ch.zli.m223.ksh20.user.controller.rest.dto.UserDto;
import ch.zli.m223.ksh20.user.controller.rest.dto.UserInputDto;
import ch.zli.m223.ksh20.user.controller.rest.dto.UserLoginDto;
import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.Booking;
import ch.zli.m223.ksh20.user.model.impl.AppUserImpl;
import ch.zli.m223.ksh20.user.security.JwtResponse;
import ch.zli.m223.ksh20.user.security.JwtUtils;
import ch.zli.m223.ksh20.user.service.BookingService;
import ch.zli.m223.ksh20.user.service.UserService;
import ch.zli.m223.ksh20.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    void register(@RequestBody UserInputDto userInput) {
        userService.addUser(userInput.firstName, userInput.lastName, userInput.email, userInput.password);

    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UserLoginDto userInput,
               HttpServletResponse response) {
        AppUser user = userService.login(userInput.email, userInput.password);
        String token = jwtUtils.generateJwtToken(user.getEmail(), user.getRole(), user.getId());
        response.setHeader("Authorization", token);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/{id}")
    Map<String, String> getUser(@PathVariable Long id,
                                @RequestHeader("Authorization") String header) {
        String token = header.split(" ")[0].trim();
        HashMap<String, String> map = new HashMap<>();
        if (jwtUtils.getRoleFromJwtToken(token).equals("admin") || jwtUtils.getIdFromJwtToken(token) == id){
            AppUser user = userService.getUserById(id);
            if (user.getEmail() == "" || user.getEmail().isEmpty()){
                return map;
            }
            map.put("firstName", user.getFirstName());
            map.put("lastName", user.getLastName());
            map.put("email", user.getEmail());
        }
        return map;
    }

    @GetMapping("/getId")
    String getId(
            @RequestHeader("Authorization") String header
    ){
        String token = header.split(" ")[0].trim();
        return jwtUtils.getIdFromJwtToken(token).toString();
    }

    @PutMapping("/{id}/update")
    void update(@RequestBody UserInputDto userInput,
                @PathVariable Long id,
                @RequestHeader("Authorization") String header) {
        String token = header.split(" ")[0].trim();
        if (jwtUtils.getRoleFromJwtToken(token).equals("admin") || jwtUtils.getIdFromJwtToken(token) == id){
            userService.updateUser(id, userInput.firstName, userInput.lastName, userInput.email);
        } else {
            System.out.println("not allowed");
        }

    }

    @DeleteMapping("/{id}/delete")
    void delete(Model model,
                @PathVariable Long id,
                @RequestHeader("Authorization") String header) {
        String token = header.split(" ")[0].trim();
        if (jwtUtils.getRoleFromJwtToken(token).equals("admin") ||jwtUtils.getIdFromJwtToken(token) == id){
            userService.deleteUser(id);
            List<AppUser> users = userService.getUserList();
            model.addAttribute("users", users);
        } else {
            System.out.println("not allowed");
        }

    }

    @GetMapping("/list")
    List<UserDto> getUserList(
            @RequestHeader("Authorization") String header
    ) {
        String token = header.split(" ")[0].trim();
        if (jwtUtils.getRoleFromJwtToken(token).equals("admin")){
            return userService.getUserList().stream()
                    .map(user -> new UserDto(user))
                    .collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/isAdmin")
    Boolean isAdmin(
            @RequestHeader("Authorization") String header
    ){
        String token = header.split(" ")[0].trim();
        return jwtUtils.getRoleFromJwtToken(token).equals("admin");
    }
}
