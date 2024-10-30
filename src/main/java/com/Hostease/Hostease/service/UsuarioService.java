package com.Hostease.Hostease.service;

import com.Hostease.Hostease.dto.EditUsuarioDTO;
import com.Hostease.Hostease.model.TipoUsuario;
import com.Hostease.Hostease.model.Usuario;
import com.Hostease.Hostease.repository.ITipoUsuarioRepository;
import com.Hostease.Hostease.repository.IUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ITipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
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

            // Validate email format
            if (!StringUtils.hasText(usuario.getEmail()) || !usuario.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new RuntimeException("Invalid email format.");
            }

            // Check for duplicate email
            Optional<Usuario> existingUser = usuarioRepository.findByEmail(usuario.getEmail());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                throw new RuntimeException("Email already in use.");
            }

            // Update fields except for roles
            userEdit.setUsername(usuario.getUsername());
            userEdit.setPassword(passwordEncoder.encode(usuario.getPassword()));
            userEdit.setEmail(usuario.getEmail());
            userEdit.setNombre(usuario.getNombre());
            userEdit.setApellido(usuario.getApellido());
            userEdit.setFecha_nacimiento(usuario.getFecha_nacimiento());
            userEdit.setFecha_modificacion(LocalDate.from(Instant.now()));

            // Save the updated user
            return usuarioRepository.save(userEdit);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
    public void actualizarUsuario(EditUsuarioDTO editUsuarioDTO, String username){
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("No existe el usuario " + username));

        usuario.setNombre(editUsuarioDTO.getNombre());
        usuario.setApellido(editUsuarioDTO.getApellido());
        usuario.setEmail(editUsuarioDTO.getEmail());

        usuarioRepository.save(usuario);



    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }


}


