package com.Hostease.Hostease.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private Long idHospedaje;
    private Long idUsuario;
    private Date fechaCheckIn;
    private Date fechaCheckOut;
    private Double cantNinos;
    private Double cantAdultos;
    private Double cantBebes;
    private Double cantMascotas;
    private Double importeTotal;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

}
