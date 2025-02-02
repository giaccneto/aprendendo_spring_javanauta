package com.javanauta.aprendendo_spring.infraestructure.repository;

import com.javanauta.aprendendo_spring.infraestructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);


    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deletePorEmail(String email);
}
