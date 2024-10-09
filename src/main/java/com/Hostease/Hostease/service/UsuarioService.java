package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.Usuario;
import com.Hostease.Hostease.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
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

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario userEdit = optionalUsuario.get();

            userEdit.setUsername(usuario.getUsername());
            userEdit.setPassword(usuario.getPassword());
            userEdit.setEmail(usuario.getEmail());
            userEdit.setNombre(usuario.getNombre());
            userEdit.setApellido(usuario.getApellido());
            userEdit.setFecha_nacimiento(usuario.getFecha_nacimiento());
            userEdit.setFecha_creacion(usuario.getFecha_creacion());
            userEdit.setFecha_modificacion(usuario.getFecha_modificacion());

            return usuarioRepository.save(userEdit);
            //userEdit.setId_tipo_usuario(usuario.getId_tipo_usuario());
            //@ManyToOne
            //@JoinColumn(name = "tipo_usuario_id")
            //private TipoUsuario tipoUsuario;

        } else {
            throw new RuntimeException("Usuario no encontrado con el ID: " + id);
        }
    }
}
