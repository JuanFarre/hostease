package com.Hostease.Hostease.controller;

import com.Hostease.Hostease.model.TipoHospedaje;
import com.Hostease.Hostease.service.ITipoHospedajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-hospedaje")
public class TipoHospedajeController {

    @Autowired
    private ITipoHospedajeService tipoHospedajeService;

    @GetMapping("/all")
    public ResponseEntity<List<TipoHospedaje>> getAllTiposHospedaje() {
        return ResponseEntity.ok(tipoHospedajeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHospedaje> findById(@PathVariable Long id) {
        Optional<TipoHospedaje> tipoHospedaje = tipoHospedajeService.findById(id);

        return tipoHospedaje.map(ResponseEntity::ok) // retorno 200ok
                .orElseGet(() -> ResponseEntity.notFound().build()); // retorno el 404
    }

    @PostMapping("/crear")
    public ResponseEntity<TipoHospedaje> crearTipoHospedaje(@RequestBody TipoHospedaje tipoHospedaje) {
        return ResponseEntity.ok(tipoHospedajeService.createTipoHospedaje(tipoHospedaje));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTipoHospedaje(@PathVariable Long id) {
        tipoHospedajeService.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TipoHospedaje> editTipoHospedaje(@RequestBody TipoHospedaje tipoHospedaje,
                                                           @PathVariable Long id) {
        return ResponseEntity.ok(tipoHospedajeService.editTipoHospedaje(tipoHospedaje, id));
    }
}
