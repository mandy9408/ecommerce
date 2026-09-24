package com.example.ecommerce.users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
//Service es la pieza que contiene la lógica de negocio de nuestra aplicación. En este caso, la lógica de negocio relacionada con los usuarios.
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder) 
    {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(UserRequest request) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new EmailAlreadyExistsException("Ya existe un usuario con ese email");
            }
        User user = new User(
                request.getName(),
                request.getEmail()
        );
        String passwordHash =
        passwordEncoder.encode(request.getPassword());

        user.setPasswordHash(passwordHash); 

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }
}
