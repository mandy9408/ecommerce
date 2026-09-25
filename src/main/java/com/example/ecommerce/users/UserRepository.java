package com.example.ecommerce.users;
import java.util.Optional;
//Repository es la pieza que nos permite interactuar con la base de datos. Nos permite guardar, buscar, eliminar y actualizar usuarios en la base de datos.
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
//puede haber un User o puede no haber ninguno.Es preferible manejar explícitamente esa posibilidad en lugar de confiar en un null
    Optional<User> findByEmail(String email);
}
