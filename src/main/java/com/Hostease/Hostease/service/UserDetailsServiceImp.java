package com.Hostease.Hostease.service;

import com.Hostease.Hostease.dto.UsuarioDTO;
import com.Hostease.Hostease.model.Usuario;
import com.Hostease.Hostease.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario registrOneCustomer(UsuarioDTO usuarioDTO) {

        validatePassword(usuarioDTO);


        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTipoUsuarios(usuarioDTO.getTipoUsuarios());
        usuario.setFecha_nacimiento(usuarioDTO.getFecha_nacimiento());
        usuario.setFecha_creacion(Instant.now());
        // Guardar el usuario en el repositorio
        return usuarioRepository.save(usuario);
    }
    private void validatePassword(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getPassword().length() < 8) {//validmos
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }

    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username));

        // Convertir los roles (tipoUsuarios) a authorities
        Set<GrantedAuthority> authorities = usuario.getTipoUsuarios().stream()
                .map(tipoUsuario -> new SimpleGrantedAuthority(tipoUsuario.getNombre()))
                .collect(Collectors.toSet());

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities)  // Asignar authorities al usuario
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .disabled(!usuario.isEnabled()) // Considerar el estado habilitado
                .build();
    }
}
