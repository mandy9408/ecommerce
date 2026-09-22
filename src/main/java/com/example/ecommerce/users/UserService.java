package com.example.ecommerce.users;
import org.springframework.stereotype.Service;

import java.util.List;
//Service es la pieza que contiene la lógica de negocio de nuestra aplicación. En este caso, la lógica de negocio relacionada con los usuarios.
@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest request) {
         User user = new User(
        request.getName(),
        request.getEmail()
    );
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
