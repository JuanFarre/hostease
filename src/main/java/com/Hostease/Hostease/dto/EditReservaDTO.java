package com.Hostease.Hostease.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditReservaDTO {


    private Date fechaCheckIn;
    private Date fechaCheckOut;
    private Double cantNinos;
    private Double cantAdultos;
    private Double cantBebes;
    private Double cantMascotas;
    private Double importeTotal;
    private LocalDateTime fechaModificacion;

}
