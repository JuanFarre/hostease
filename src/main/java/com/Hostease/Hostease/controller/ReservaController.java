package com.Hostease.Hostease.controller;

import com.Hostease.Hostease.model.PKReserva;
import com.Hostease.Hostease.model.Reserva;
import com.Hostease.Hostease.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @GetMapping("/all")
    public List<Reserva> getAllReservas() {
        return reservaService.findAll();
    }

    @GetMapping("/{idHospedaje}/{idUsuario}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long idHospedaje, @PathVariable Long idUsuario) {
        PKReserva id = new PKReserva(idHospedaje, idUsuario);
        Optional<Reserva> reserva = reservaService.findById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.save(reserva));
    }

    @PutMapping("/edit/{idHospedaje}/{idUsuario}")
    public ResponseEntity<Reserva> updateReserva(@RequestBody Reserva reserva, @PathVariable Long idHospedaje, @PathVariable Long idUsuario) {
        PKReserva id = new PKReserva(idHospedaje, idUsuario);
        try {
            Reserva updatedReserva = reservaService.edit(reserva, id);
            return ResponseEntity.ok(updatedReserva);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{idHospedaje}/{idUsuario}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long idHospedaje, @PathVariable Long idUsuario) {
        PKReserva id = new PKReserva(idHospedaje, idUsuario);
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}