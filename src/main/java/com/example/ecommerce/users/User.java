package com.example.ecommerce.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//Clase Java que representa un usuario de nuestro sistema.
@Entity //Esta clase representa información que quiero guardar en una BD
@Table(name = "users")

public class User {
    @Id //significa que ese campo es el identificador de la entidad.Cada usuario tendrá un identificador único.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Quiero que la base de datos genere automáticamente el ID.
    private Long id;
    
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
