package com.Hostease.Hostease.repository;

import com.Hostease.Hostease.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByUsername(String username);

    public  Optional<Usuario> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


}
