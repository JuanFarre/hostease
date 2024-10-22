package com.Hostease.Hostease.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditUsuarioDTO {



    private String username;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Email(message = "El correo electrónico debe tener un formato válido")
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    private String email;

}
