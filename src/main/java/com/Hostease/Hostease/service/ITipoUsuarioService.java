package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface ITipoUsuarioService {

    public Optional<TipoUsuario> findById(Long id);

    public List<TipoUsuario> findAll();

    public void deleteById(Long id);

    public TipoUsuario createTipoUsuario(TipoUsuario tipoUsuario);

    public TipoUsuario editTipoUsuario(TipoUsuario tipoUsuario, Long id);
}

