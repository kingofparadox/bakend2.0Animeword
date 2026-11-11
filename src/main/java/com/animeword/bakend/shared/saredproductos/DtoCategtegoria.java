package com.animeword.bakend.shared.saredproductos;

import jakarta.validation.constraints.NotBlank;

public record DtoCategtegoria(
    @NotBlank (message = "LA categoria debe tener un nombre")
    String nombre

) {

}
