package com.Hostease.Hostease.dto;


import com.Hostease.Hostease.model.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {

    private String token;
    private Long id;
    private String username;
    private String email;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String fecha_creacion;
    private String fecha_modificacion;
    private Set<TipoUsuario> tipoUsuarios;
}
