package web.rest;

import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class ApplicationRestController {

    private final UserService userService;

    public ApplicationRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        return userService.listUsers();
    }

    @GetMapping("/users/{username}")
    public User getUserByName(@PathVariable String username){
        return userService.getByName(username);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user){
        userService.add(user, user.getRoles());
        return user;
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User user){
        userService.update(user, user.getRoles());
    }

    @DeleteMapping("/users/{username}")
    public void deleteUser(@PathVariable String username){
        userService.removeByUsername(username);
    }
}
