package com.Hostease.Hostease.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Table(name = "hospedaje")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hospedaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String imagen;

    @Column(name = "precio_por_noche", precision = 8, scale = 2)
    private BigDecimal precioPorNoche;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;


    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_hospedaje")
    private TipoHospedaje tipoHospedaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;

    @ManyToMany(mappedBy = "hospedajes", fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
    @JsonIgnore
    private Set<Servicio> servicios = new HashSet<>();


}
