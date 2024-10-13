package com.Hostease.Hostease.controller;

import com.Hostease.Hostease.model.Pais;
import com.Hostease.Hostease.service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private IPaisService paisService;

    @GetMapping("/all")
    public ResponseEntity<List<Pais>> getAllPaises() {
        return ResponseEntity.ok(paisService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> findById(@PathVariable Long id) {
        Optional<Pais> pais = paisService.findById(id);
        return pais.map(ResponseEntity::ok) // retorno 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // retorno 404
    }

    @PostMapping("/crear")
    public ResponseEntity<Pais> crearPais(@RequestBody Pais pais) {
        try {
            Pais nuevoPais = paisService.createPais(pais);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPais);
        } catch (IllegalArgumentException e) {
            // Maneja el error si el nombre del país es inválido o vacío
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            // Captura cualquier otro error y devuelve un mensaje genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/delete/{id}")
    public void deletePais(@PathVariable Long id) {
        paisService.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Pais> editPais(@RequestBody Pais pais, @PathVariable Long id) {
        return ResponseEntity.ok(paisService.editPais(pais, id));
    }
}