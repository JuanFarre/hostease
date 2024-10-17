package com.Hostease.Hostease.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Pattern(regexp = ".+@.+\\..+", message = "El correo electrónico debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "La contraseña debe contener al menos un número, una letra minúscula y una mayúscula")
    private String password;

    @NotBlank(message = "Debe seleccionar un rol")
    private String rol;

    // validamos que sea inquilino o anfitrion.
    public boolean isValidRol() {
        return rol.equals("inquilino") || rol.equals("anfitrión");
    }
}
