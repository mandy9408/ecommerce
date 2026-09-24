package com.example.ecommerce.users;
import org.hibernate.internal.util.Optional;
//Repository es la pieza que nos permite interactuar con la base de datos. Nos permite guardar, buscar, eliminar y actualizar usuarios en la base de datos.
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
