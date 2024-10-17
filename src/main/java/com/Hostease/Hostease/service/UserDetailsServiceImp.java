package com.Hostease.Hostease.service;

import com.Hostease.Hostease.dto.UsuarioDTO;
import com.Hostease.Hostease.model.Usuario;
import com.Hostease.Hostease.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario registrOneCustomer(UsuarioDTO usuarioDTO) {
        // Validar la contraseña (si tienes algún criterio)
        validatePassword(usuarioDTO);

        // Convertir el DTO a una entidad Usuario
        Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // Encriptar la contraseña
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());

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
        return null;
    }
}
