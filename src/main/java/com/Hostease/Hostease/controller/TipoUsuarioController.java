package com.Hostease.Hostease.controller;


import com.Hostease.Hostease.model.TipoUsuario;
import com.Hostease.Hostease.service.ITipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-usuarios")
public class TipoUsuarioController {

    @Autowired
    private ITipoUsuarioService tipoUsuarioService;

    @GetMapping("/all")
    public ResponseEntity<List<TipoUsuario>> getAllTipoUsuarios() {
        return ResponseEntity.ok(tipoUsuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> findById(@PathVariable Long id) {
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioService.findById(id);
        return tipoUsuario.map(ResponseEntity::ok) // retorno 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // retorno 404
    }

    @PostMapping("/crear")
    public ResponseEntity<TipoUsuario> crearTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        return ResponseEntity.ok(tipoUsuarioService.createTipoUsuario(tipoUsuario));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTipoUsuario(@PathVariable Long id) {
        tipoUsuarioService.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TipoUsuario> editTipoUsuario(@RequestBody TipoUsuario tipoUsuario,
                                                       @PathVariable Long id) {
        return ResponseEntity.ok(tipoUsuarioService.editTipoUsuario(tipoUsuario, id));
    }
}
