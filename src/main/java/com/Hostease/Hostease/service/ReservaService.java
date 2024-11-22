package com.Hostease.Hostease.service;
import com.Hostease.Hostease.model.Hospedaje;
import com.Hostease.Hostease.model.PKReserva;
import com.Hostease.Hostease.model.Reserva;
import com.Hostease.Hostease.repository.IHospedajeRepository;
import com.Hostease.Hostease.repository.IReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private IHospedajeRepository hospedajeRepository;

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> findById(PKReserva id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva save(Reserva reserva) {
        Hospedaje hospedaje = hospedajeRepository.findById(reserva.getId().getIdHospedaje())
                .orElseThrow(() -> new EntityNotFoundException("Hospedaje no encontrado con el ID: " + reserva.getId().getIdHospedaje()));
        reserva.setImporteTotal(reserva.calcularImporteTotal(hospedaje));
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva edit(Reserva reserva, PKReserva id) {
        // Verificamos si la reserva existe en la base de datos
        Reserva reservaEdit = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con el ID: " + id));

        // Actualizamos los campos de la reserva
        if (reserva.getFechaCheckIn() != null) {
            reservaEdit.setFechaCheckIn(reserva.getFechaCheckIn());
        }
        if (reserva.getFechaCheckOut() != null) {
            reservaEdit.setFechaCheckOut(reserva.getFechaCheckOut());
        }
        if (reserva.getCantNinos() != null) {
            reservaEdit.setCantNinos(reserva.getCantNinos());
        }
        if (reserva.getCantAdultos() != null) {
            reservaEdit.setCantAdultos(reserva.getCantAdultos());
        }
        if (reserva.getCantBebes() != null) {
            reservaEdit.setCantBebes(reserva.getCantBebes());
        }
        if (reserva.getCantMascotas() != null) {
            reservaEdit.setCantMascotas(reserva.getCantMascotas());
        }
        if (reserva.getImporteTotal() != null) {
            reservaEdit.setImporteTotal(reserva.getImporteTotal());
        }
        if (reserva.getFechaCreacion() != null) {
            reservaEdit.setFechaCreacion(reserva.getFechaCreacion());
        }


        reservaEdit.setFechaModificacion(LocalDateTime.now());


        // Guardar la reserva editada
        return reservaRepository.save(reservaEdit);
    }


    @Override
    public void deleteById(PKReserva id) {
        reservaRepository.deleteById(id);
    }


    @Override
    public boolean HospedajeDisponible(Long idHospedaje, Date fechaCheckIn, Date fechaCheckOut) {
        List<Reserva> reservas = reservaRepository.findReservasByHospedajeAndDates(idHospedaje, fechaCheckIn, fechaCheckOut);
        return reservas.isEmpty();
    }
    // ReservaService.java
    public List<Reserva> findReservasByUsuarioId(Long idUsuario) {
        return reservaRepository.findById_IdUsuario(idUsuario);
    }

}