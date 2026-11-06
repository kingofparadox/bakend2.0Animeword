package com.animeword.bakend.shared;


import java.time.LocalDateTime;

import com.animeword.bakend.models.UserModel;

import jakarta.validation.constraints.NotBlank;

public  record DtoIncidente(
    @NotBlank (message ="el usuario no puede esatr vacio")
    UserModel user,
    @NotBlank (message = "el incidente debe tener descripcion")
    String descripcionIncidente,
    LocalDateTime Fincio
) {
}