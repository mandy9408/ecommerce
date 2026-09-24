package com.example.ecommerce.users;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173") //Permite que el front pueda hacer peticiones al back.
//Recibir peticiones HTTP
@RestController
@RequestMapping("/users")
public class UserController {
       private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    } 
}
