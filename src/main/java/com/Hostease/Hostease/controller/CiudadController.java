package com.Hostease.Hostease.controller;

import com.Hostease.Hostease.model.Ciudad;
import com.Hostease.Hostease.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    @Autowired
    private ICiudadService ciudadService;



    @GetMapping("/all")
    public ResponseEntity<List<Ciudad>> getAllCiudades() {
        return ResponseEntity.ok(ciudadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> findById(@PathVariable Long id) {
        Optional<Ciudad> ciudad = ciudadService.findById(id);
        return ciudad.map(ResponseEntity::ok) // retorno 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // retorno 404
    }

    @PostMapping("/crear")
    public ResponseEntity<Ciudad> crearCiudad(@RequestBody Ciudad ciudad) {
        return ResponseEntity.ok(ciudadService.createCiudad(ciudad));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCiudad(@PathVariable Long id) {
        ciudadService.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ciudad> editCiudad(@RequestBody Ciudad ciudad, @PathVariable Long id) {
        return ResponseEntity.ok(ciudadService.editCiudad(ciudad, id));
    }
}