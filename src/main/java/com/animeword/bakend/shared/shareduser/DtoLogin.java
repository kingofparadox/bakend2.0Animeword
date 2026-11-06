package com.animeword.bakend.shared.shareduser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoLogin(
    @NotBlank (message = "El correo es obligatorio")
    @Email (message = "Debe ser un formato de correo válido")
    String correo,
    @NotBlank(message = "La contraseña es obligatoria")
    String password 
) {}
