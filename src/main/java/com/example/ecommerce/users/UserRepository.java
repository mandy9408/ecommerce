package com.example.ecommerce.users;
//Repository es la pieza que nos permite interactuar con la base de datos. Nos permite guardar, buscar, eliminar y actualizar usuarios en la base de datos.
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
