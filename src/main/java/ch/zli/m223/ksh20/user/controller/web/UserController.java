package ch.zli.m223.ksh20.user.controller.web;
import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
   @Autowired
    private UserService userService;

    @GetMapping("/list")
    String getUserList (Model model) {
        List<AppUser> users = userService.getUserList();
        model.addAttribute("users", users);
        return "userList";
    }

    /**@GetMapping("/web/users/{id}")
    String getUser (Model model, @PathVariable Long id){
        AppUser user = userService.getUserById(id);
        model.addAttribute("userId", user);
        return "user";
    }

    @PostMapping("/web/users")
    String addUser(Model model,
                   @RequestHeader String userName,
                   @RequestHeader String password
                   ) {
        //List<AppUser> users = userService.addUser(userName, password);
//        model.addAttribute("users", users);
        return "userList";
    }*/

    /**@DeleteMapping("/web/users/{id}/delete")
    String deleteUser (Model model, @PathVariable Long id){
        userService.deleteUser(id);
        List<AppUser> users = userService.getUserList();
        model.addAttribute("users", users);
        return "userList";
    }*/

    @GetMapping("/error")
    String getError (){
        return "error";
    }
}
