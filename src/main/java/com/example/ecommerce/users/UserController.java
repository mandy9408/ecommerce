package com.example.ecommerce.users;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
//Recibir peticiones HTTP
@RestController
@RequestMapping("/users")
public class UserController {
       private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    } 
}
