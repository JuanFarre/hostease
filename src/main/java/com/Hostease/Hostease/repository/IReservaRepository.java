package com.Hostease.Hostease.repository;

import com.Hostease.Hostease.model.PKReserva;
import com.Hostease.Hostease.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, PKReserva> {
}
