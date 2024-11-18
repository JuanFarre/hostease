package com.Hostease.Hostease.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditHospedajeDTO {


    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "El precio por noche no puede estar vacío")
    @Positive(message = "El precio por noche debe ser un valor positivo")
    private BigDecimal precioPorNoche;

    @NotNull(message = "Debe proporcionar una imagen")
    private String imagen;

    @NotNull(message = "El tipo de hospedaje no puede estar vacío")
    private Long tipoHospedajeId;

    @NotNull(message = "La ciudad no puede estar vacía")
    private Long ciudadId;

    @NotNull(message = "Debe seleccionar al menos un servicio")
    private List<Long> serviciosIds;
}
