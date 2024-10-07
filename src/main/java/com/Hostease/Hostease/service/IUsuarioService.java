package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    public Usuario findById(Long id);

    public List<Usuario> findAll();

    public void deleteById(Long id);

    public Usuario createUsuario(Usuario usuario);

    public Usuario editUsuario(Usuario usuario, Long id);



}
