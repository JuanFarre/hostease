package com.Hostease.Hostease.controller;
import com.Hostease.Hostease.dto.AuthRequest;
import com.Hostease.Hostease.dto.AuthResponse;
import com.Hostease.Hostease.dto.UsuarioDTO;
import com.Hostease.Hostease.model.TipoUsuario;
import com.Hostease.Hostease.model.Usuario;
import com.Hostease.Hostease.service.ITipoUsuarioService;
import com.Hostease.Hostease.service.IUsuarioService;
import com.Hostease.Hostease.service.JwtService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ITipoUsuarioService tipoUsuarioService;

    @Autowired
    private IUsuarioService usuarioService;


    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails, new HashMap<>());
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioService.existsByUsername(usuarioDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El nombre de usuario ya está en uso");
        }
        if (usuarioService.existsByEmail(usuarioDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo electrónico ya está en uso");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setFecha_nacimiento(usuarioDTO.getFecha_nacimiento());
        usuario.setFecha_creacion(Instant.now());

        // Manejo de TipoUsuario
        Set<TipoUsuario> tiposUsuariosCargados = new HashSet<>();
        if (usuarioDTO.getTipoUsuarios() != null) {
            for (TipoUsuario tipoUsuario : usuarioDTO.getTipoUsuarios()) {
                TipoUsuario tipoUsuarioCargado = tipoUsuarioService.findById(tipoUsuario.getId())
                        .orElseThrow(() -> new RuntimeException("TipoUsuario no encontrado: " + tipoUsuario.getId()));
                tiposUsuariosCargados.add(tipoUsuarioCargado);
            }
        }
        usuario.setTipoUsuarios(tiposUsuariosCargados);

        // Crea el usuario
        Usuario nuevoUsuario = usuarioService.createUsuario(usuario);

        // Genera y devuelve el token de autenticación
        String token = jwtService.generateToken(nuevoUsuario, new HashMap<>());
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
