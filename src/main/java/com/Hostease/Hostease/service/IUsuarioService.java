package com.Hostease.Hostease.service;

import com.Hostease.Hostease.dto.EditUsuarioDTO;
import com.Hostease.Hostease.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public Optional<Usuario> findById(Long id);

    public Optional<Usuario> findByUsername(String username);

    public List<Usuario> findAll();

    public void deleteById(Long id);

    public Usuario createUsuario(Usuario usuario);

    public Usuario editUsuario(Usuario usuario, Long id);

    void actualizarUsuario(EditUsuarioDTO editUsuarioDTO, String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);



}
