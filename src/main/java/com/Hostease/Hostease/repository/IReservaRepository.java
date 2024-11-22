package com.Hostease.Hostease.repository;

import com.Hostease.Hostease.model.PKReserva;
import com.Hostease.Hostease.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, PKReserva> {

        List<Reserva> findById_IdUsuario(Long idUsuario);


        @Query("SELECT r FROM Reserva r WHERE r.id.idHospedaje = :idHospedaje AND " +
                "((:fechaCheckIn BETWEEN r.fechaCheckIn AND r.fechaCheckOut) OR " +
                "(:fechaCheckOut BETWEEN r.fechaCheckIn AND r.fechaCheckOut) OR " +
                "(r.fechaCheckIn BETWEEN :fechaCheckIn AND :fechaCheckOut) OR " +
                "(r.fechaCheckOut BETWEEN :fechaCheckIn AND :fechaCheckOut))")
        List<Reserva> findReservasByHospedajeAndDates(@Param("idHospedaje") Long idHospedaje,
                                                      @Param("fechaCheckIn") Date fechaCheckIn,
                                                      @Param("fechaCheckOut") Date fechaCheckOut);
}
