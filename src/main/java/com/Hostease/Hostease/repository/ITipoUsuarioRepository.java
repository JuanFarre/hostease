package com.Hostease.Hostease.repository;

import com.Hostease.Hostease.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
}
