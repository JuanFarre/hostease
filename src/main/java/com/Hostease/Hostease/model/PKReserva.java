package com.Hostease.Hostease.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PKReserva implements Serializable {

    @Column(name = "id_hospedaje")
    private Long idHospedaje;
    @Column(name = "id_usuario")
    private Long idUsuario;
}
