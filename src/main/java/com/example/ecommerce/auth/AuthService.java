package com.example.ecommerce.auth;

import com.example.ecommerce.users.User;
import com.example.ecommerce.users.UserRepository;
import com.example.ecommerce.users.UserResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public UserResponse login(
            LoginRequest request,
            HttpServletRequest httpRequest
    ) {

        Authentication authentication;

        try {

            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

        } catch (AuthenticationException ex) {

            throw new InvalidCredentialsException(
                    "Email o contraseña incorrectos"
            );
        }


        // Creamos un SecurityContext vacío
        SecurityContext context =
                SecurityContextHolder.createEmptyContext();

        // Guardamos dentro al usuario autenticado
        context.setAuthentication(authentication);

        // Lo establecemos también para la petición actual
        SecurityContextHolder.setContext(context);

        // Creamos/obtenemos la sesión HTTP
        // y guardamos el SecurityContext dentro
        httpRequest
                .getSession(true)
                .setAttribute(
                        HttpSessionSecurityContextRepository
                                .SPRING_SECURITY_CONTEXT_KEY,
                        context
                );


        // Recuperamos nuestros datos de usuario
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Email o contraseña incorrectos"
                        )
                );


        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }


    public UserResponse getCurrentUser(Authentication authentication) {

    if (authentication == null ||
            !authentication.isAuthenticated() ||
            authentication.getName().equals("anonymousUser")) {

        throw new UnauthenticatedException(
                "No hay ningún usuario autenticado"
        );
    }

    String email = authentication.getName();

    User user = userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                    new UnauthenticatedException(
                            "Usuario autenticado no encontrado"
                    )
            );

    return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail()
    );
}
}