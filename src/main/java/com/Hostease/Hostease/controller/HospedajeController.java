package com.Hostease.Hostease.controller;

import com.Hostease.Hostease.model.Ciudad;
import com.Hostease.Hostease.model.Hospedaje;
import com.Hostease.Hostease.model.TipoHospedaje;
import com.Hostease.Hostease.service.ICiudadService;
import com.Hostease.Hostease.service.IHospedajeService;
import com.Hostease.Hostease.service.ITipoHospedajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospedajes")
public class HospedajeController {

    @Autowired
    private IHospedajeService hospedajeService;

    @Autowired
    private ITipoHospedajeService tipoHospedajeService;

    @Autowired
    private ICiudadService ciudadService;

    // Método para crear un nuevo hospedaje
    @PostMapping("/crear")
    public ResponseEntity<Hospedaje> createHospedaje(@RequestBody Hospedaje hospedaje) {
        Optional<TipoHospedaje> optionalTipoHospedaje = tipoHospedajeService.findById(hospedaje.getTipoHospedaje().getId());
        Optional<Ciudad> optionalCiudad = ciudadService.findById(hospedaje.getCiudad().getId());

        if (optionalTipoHospedaje.isPresent() && optionalCiudad.isPresent()) {
            hospedaje.setTipoHospedaje(optionalTipoHospedaje.get());
            hospedaje.setCiudad(optionalCiudad.get());
            hospedaje.setFechaCreacion(LocalDateTime.now());
            Hospedaje newHospedaje = hospedajeService.createHospedaje(hospedaje);
            return ResponseEntity.ok(newHospedaje);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Método para editar un hospedaje existente
    @PutMapping("/edit/{id}")
    public ResponseEntity<Hospedaje> editHospedaje(@RequestBody Hospedaje hospedaje, @PathVariable Long id) {
        Optional<TipoHospedaje> optionalTipoHospedaje = tipoHospedajeService.findById(hospedaje.getTipoHospedaje().getId());
        Optional<Ciudad> optionalCiudad = ciudadService.findById(hospedaje.getCiudad().getId());

        if (optionalTipoHospedaje.isPresent() && optionalCiudad.isPresent()) {
            hospedaje.setTipoHospedaje(optionalTipoHospedaje.get());
            hospedaje.setCiudad(optionalCiudad.get());
            hospedaje.setFechaModificacion(LocalDateTime.now());
            Hospedaje updatedHospedaje = hospedajeService.editHospedaje(hospedaje, id);
            return ResponseEntity.ok(updatedHospedaje);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Método para obtener todos los hospedajes
    @GetMapping("/all")
    public ResponseEntity<List<Hospedaje>> getAllHospedajes() {
        return ResponseEntity.ok(hospedajeService.findAll());
    }

    // Método para obtener un hospedaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<Hospedaje> getHospedajeById(@PathVariable Long id) {
        Optional<Hospedaje> hospedaje = hospedajeService.findById(id);
        return hospedaje.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para eliminar un hospedaje por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHospedaje(@PathVariable Long id) {
        if (hospedajeService.findById(id).isPresent()) {
            hospedajeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
