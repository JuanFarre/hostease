package com.Hostease.Hostease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;


@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column()
    private LocalDate fecha_nacimiento;
    private Instant fecha_creacion;
    private LocalDate fecha_modificacion;
    private Long id_tipo_usuario;
}
