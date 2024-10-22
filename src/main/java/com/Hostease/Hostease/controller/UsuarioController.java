package com.Hostease.Hostease.controller;


import com.Hostease.Hostease.dto.EditUsuarioDTO;
import com.Hostease.Hostease.model.TipoUsuario;
import com.Hostease.Hostease.model.Usuario;
import com.Hostease.Hostease.service.ITipoUsuarioService;
import com.Hostease.Hostease.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;



    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){

        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){

        Optional<Usuario> usuario = usuarioService.findById(id);

        return usuario.map(ResponseEntity::ok)//retorno 200ok
                .orElseGet(() -> ResponseEntity.notFound().build());// retorno el 404

    }
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){

        return ResponseEntity.ok(usuarioService.createUsuario(usuario));

    }
    @DeleteMapping("/delete/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
        try {
            usuarioService.editUsuario(usuario, id);
            return ResponseEntity.ok("User profile updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/modificar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> updateUser(@Validated @RequestBody EditUsuarioDTO editUsuarioDTO, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            System.out.println("UserDetails es null");
            return ResponseEntity.status(401).body("Usuario no autenticado");
        }

        String authenticatedUsername = userDetails.getUsername();
        System.out.println("Usuario autenticado: " + authenticatedUsername);

        // Verificar si el usuario autenticado es el mismo que el usuario a actualizar
        if (!authenticatedUsername.equals(editUsuarioDTO.getUsername())) {
            return ResponseEntity.status(403).body("No tienes permiso para modificar este perfil");
        }

        usuarioService.actualizarUsuario(editUsuarioDTO, authenticatedUsername);
        return ResponseEntity.ok("Perfil actualizado con éxito");
    }

}
