package com.Hostease.Hostease.controller;


import com.Hostease.Hostease.dto.ServicioDTO;
import com.Hostease.Hostease.model.Servicio;
import com.Hostease.Hostease.service.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/servicios")
@RestController
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    // Obtener todos los servicios
    @GetMapping("/all")
    public ResponseEntity<List<Servicio>> getAllServicios() {
        List<Servicio> servicios = servicioService.findAll();
        return ResponseEntity.ok(servicios);
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        Optional<Servicio> servicio = servicioService.findById(id);
        return servicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/crear")
    public ResponseEntity<ServicioDTO> createServicio(@RequestBody ServicioDTO servicioDTO) {
        Servicio servicio = servicioService.convertirAModelo(servicioDTO);
        Servicio createdServicio = servicioService.createServicio(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioService.convertirADTO(createdServicio));
    }

    // Editar un servicio existente
    @PutMapping("/edit/{id}")
    public ResponseEntity<Servicio> editServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        try {
            Servicio updatedServicio = servicioService.editServicio(servicio, id);
            return ResponseEntity.ok(updatedServicio);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Servicio no encontrado
        }
    }

    // Eliminar un servicio por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        try {
            servicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Servicio no encontrado
        }
    }
}

