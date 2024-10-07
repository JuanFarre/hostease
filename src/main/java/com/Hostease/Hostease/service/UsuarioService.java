package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.Usuario;
import com.Hostease.Hostease.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario editUsuario(Usuario usuario, Long id) {
        Usuario userEdit = this.findById(id);

        userEdit.setNombre




    }
}
