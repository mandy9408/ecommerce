package com.example.ecommerce.users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//UserRequest no es una tabla ni una Entity. Es un objeto que representa lo que nuestra API acepta cuando alguien quiere crear un usuario.
public class UserRequest {
    
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

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
