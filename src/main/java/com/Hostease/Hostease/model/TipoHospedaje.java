package com.Hostease.Hostease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "tipo_hospedaje")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoHospedaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

}
