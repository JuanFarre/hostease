package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.PKReserva;
import com.Hostease.Hostease.model.Reserva;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReservaService {
    public List<Reserva> findAll();

    public Optional<Reserva> findById(PKReserva id);
    public Reserva save(Reserva reserva);

    public Reserva edit(Reserva Reserva, PKReserva id);

    public void deleteById(PKReserva id);

    public List<Reserva> findReservasByUsuarioId(Long idUsuario);
    public boolean HospedajeDisponible(Long idHospedaje, Date fechaCheckIn, Date fechaCheckOut);
}
