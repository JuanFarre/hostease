package com.Hostease.Hostease.dto;

import com.Hostease.Hostease.model.TipoUsuario;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {


    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @NotBlank(message = "El usuario no puede estar vacío")
    private String username;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email
    private String email;

    private LocalDate fecha_nacimiento;

    private Instant fecha_creacion;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "La contraseña debe contener al menos un número, una letra minúscula y una mayúscula")
    private String password;

    private Set<TipoUsuario> tipoUsuarios;

    // validamos que sea inquilino o anfitrion.
}
