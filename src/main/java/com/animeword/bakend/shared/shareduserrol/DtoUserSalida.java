package com.animeword.bakend.shared.shareduserrol;

import jakarta.validation.constraints.NotBlank;

public record DtoUserSalida(
    @NotBlank (message = "el nombre debe estar escrito")
    String nombreCompleto,
    @NotBlank (message="el rut deve tener datos")
    String rut,
    @NotBlank (message="el correo no puede estar vacio")
    String correo,
    @NotBlank (message = "rol no se puede tener el roll vacio")
    String rol
) {
}
