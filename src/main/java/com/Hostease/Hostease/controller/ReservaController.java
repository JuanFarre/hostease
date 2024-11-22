package com.Hostease.Hostease.controller;

import com.Hostease.Hostease.dto.EditReservaDTO;
import com.Hostease.Hostease.dto.ReservaDTO;
import com.Hostease.Hostease.model.PKReserva;
import com.Hostease.Hostease.model.Reserva;
import com.Hostease.Hostease.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @PreAuthorize("hasRole('INQUILINO')")
    @PostMapping("/crear")
    public ResponseEntity<Reserva> createReserva(@RequestBody ReservaDTO reservaDTO) {
        if (!reservaService.HospedajeDisponible(reservaDTO.getIdHospedaje(), reservaDTO.getFechaCheckIn(), reservaDTO.getFechaCheckOut())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Reserva reserva = new Reserva();
        PKReserva pkReserva = new PKReserva();
        pkReserva.setIdHospedaje(reservaDTO.getIdHospedaje());
        pkReserva.setIdUsuario(reservaDTO.getIdUsuario());
        reserva.setId(pkReserva);
        reserva.setFechaCheckIn(reservaDTO.getFechaCheckIn());
        reserva.setFechaCheckOut(reservaDTO.getFechaCheckOut());
        reserva.setCantNinos(reservaDTO.getCantNinos());
        reserva.setCantAdultos(reservaDTO.getCantAdultos());
        reserva.setCantBebes(reservaDTO.getCantBebes());
        reserva.setCantMascotas(reservaDTO.getCantMascotas());
        reserva.setImporteTotal(reservaDTO.getImporteTotal());
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setFechaModificacion(reservaDTO.getFechaModificacion());

        Reserva createdReserva = reservaService.save(reserva);
        return ResponseEntity.ok(createdReserva);
    }

    @PreAuthorize("hasRole('INQUILINO')")
    @PutMapping("/modificar/{idHospedaje}/{idUsuario}")
    public ResponseEntity<Reserva> modificarReserva(@RequestBody EditReservaDTO editReservaDTO, @PathVariable Long idHospedaje, @PathVariable Long idUsuario) {
        PKReserva id = new PKReserva(idHospedaje, idUsuario);
        Optional<Reserva> existingReserva = reservaService.findById(id);

        if (existingReserva.isPresent()) {
            Reserva reserva = existingReserva.get();

            if (!reservaService.HospedajeDisponible(idHospedaje, editReservaDTO.getFechaCheckIn(), editReservaDTO.getFechaCheckOut())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }

            reserva.setFechaCheckIn(editReservaDTO.getFechaCheckIn());
            reserva.setFechaCheckOut(editReservaDTO.getFechaCheckOut());
            reserva.setCantNinos(editReservaDTO.getCantNinos());
            reserva.setCantAdultos(editReservaDTO.getCantAdultos());
            reserva.setCantBebes(editReservaDTO.getCantBebes());
            reserva.setCantMascotas(editReservaDTO.getCantMascotas());
            reserva.setFechaModificacion(LocalDateTime.now());
            Reserva updatedReserva = reservaService.save(reserva);

            return ResponseEntity.ok(updatedReserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('INQUILINO')")
    @DeleteMapping("/delete/{idHospedaje}/{idUsuario}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long idHospedaje, @PathVariable Long idUsuario) {
        PKReserva id = new PKReserva(idHospedaje, idUsuario);
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    // ReservaController.java
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorUsuario(@PathVariable Long idUsuario) {
        List<Reserva> reservas = reservaService.findReservasByUsuarioId(idUsuario);
        List<ReservaDTO> reservaDTOs = reservas.stream()
                .map(reserva -> new ReservaDTO(
                        reserva.getId().getIdHospedaje(),
                        reserva.getId().getIdUsuario(),
                        reserva.getFechaCheckIn(),
                        reserva.getFechaCheckOut(),
                        reserva.getCantNinos(),
                        reserva.getCantAdultos(),
                        reserva.getCantBebes(),
                        reserva.getCantMascotas(),
                        reserva.getImporteTotal(),
                        reserva.getFechaCreacion(),
                        reserva.getFechaModificacion()
                ))
                .toList();
        return ResponseEntity.ok(reservaDTOs);
    }


}