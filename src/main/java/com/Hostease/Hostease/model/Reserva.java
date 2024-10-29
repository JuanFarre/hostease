package com.Hostease.Hostease.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.*;
import java.util.Date;

@Table(name = "reserva")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @EmbeddedId
    private PKReserva id;

    @Column(name = "fecha_check_in")
    private Date fechaCheckIn;

    @Column(name = "fecha_check_out")
    private Date fechaCheckOut;

    @Column(name = "cant_niños")
    private Double cantNinos;

    @Column(name = "cant_adultos")
    private Double cantAdultos;

    @Column(name = "cant_bebes")
    private Double cantBebes;

    @Column(name = "cant_mascotas")
    private Double cantMascotas;

    @Column(name = "importe_total")
    private Double importeTotal;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    public Integer calcularDiasReserva() {
        if (fechaCheckIn == null || fechaCheckOut == null) {
            return 0;
        }
        LocalDate checkIn = fechaCheckIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkOut = fechaCheckOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay()).toDays();
    }
    public Double calcularImporteTotal(Hospedaje hospedaje) {
        int diasReserva = calcularDiasReserva();
        if (hospedaje == null || hospedaje.getPrecioPorNoche() == null) {
            return 0.0;
        }
        BigDecimal diasReservaBD = BigDecimal.valueOf(diasReserva);
        BigDecimal importeTotal = diasReservaBD.multiply(hospedaje.getPrecioPorNoche());
        return importeTotal.doubleValue();
    }

}




