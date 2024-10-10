package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.TipoUsuario;
import com.Hostease.Hostease.model.Usuario;
import com.Hostease.Hostease.repository.ITipoUsuarioRepository;
import com.Hostease.Hostease.repository.IUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ITipoUsuarioRepository tipoUsuarioRepository;

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

        Set<TipoUsuario> tiposUsuariosCargados = new HashSet<>();

        for (TipoUsuario tipoUsuario : usuario.getTipoUsuarios()) {
            TipoUsuario tipoUsuarioCargado = tipoUsuarioRepository.findById(tipoUsuario.getId())
                    .orElseThrow(() -> new RuntimeException("TipoUsuario no encontrado: " + tipoUsuario.getId()));
            tiposUsuariosCargados.add(tipoUsuarioCargado);
        }

        usuario.setTipoUsuarios(tiposUsuariosCargados);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario editUsuario(Usuario usuario, Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario userEdit = optionalUsuario.get();

            // Actualiza los campos del usuario
            userEdit.setUsername(usuario.getUsername());
            userEdit.setPassword(usuario.getPassword());
            userEdit.setEmail(usuario.getEmail());
            userEdit.setNombre(usuario.getNombre());
            userEdit.setApellido(usuario.getApellido());
            userEdit.setFecha_nacimiento(usuario.getFecha_nacimiento());
            userEdit.setFecha_creacion(usuario.getFecha_creacion());
            userEdit.setFecha_modificacion(usuario.getFecha_modificacion());

            // Cargar los tipoUsuarios desde la base de datos
            Set<TipoUsuario> tiposUsuariosCargados = new HashSet<>();
            for (TipoUsuario tipoUsuario : usuario.getTipoUsuarios()) {
                TipoUsuario tipoUsuarioCargado = tipoUsuarioRepository.findById(tipoUsuario.getId())
                        .orElseThrow(() -> new RuntimeException("TipoUsuario no encontrado: " + tipoUsuario.getId()));
                tiposUsuariosCargados.add(tipoUsuarioCargado);
            }

            // Establecer los tipoUsuarios en el usuario editado
            userEdit.setTipoUsuarios(tiposUsuariosCargados);

            // Guardar el usuario editado
            return usuarioRepository.save(userEdit);
        } else {
            throw new RuntimeException("Usuario no encontrado con el ID: " + id);
        }
    }

}
