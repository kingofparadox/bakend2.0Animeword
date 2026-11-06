package com.animeword.bakend.shared.shareduser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoUserEntrada(
    @NotBlank (message = "nombre no pude estar vacio")
    String nombre,
    @NotBlank (message = "apellido no pude estar vacio")
    String apellido,
    @NotBlank (message = "rut no pude estar vacio")
    String rut,
    @NotBlank (message = "nobre no pude estar vacio")
    @Email (message = "debe ser un correo valido gmail por ejemplo")
    String correo,
    @NotBlank (message = "debe ser completo la contraseña")
    String password,
    @NotBlank (message = "el rol debe de estar escrito")
    String rol
) {
    
}
