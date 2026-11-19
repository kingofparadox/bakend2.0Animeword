package com.animeword.bakend.shared.sharedUserRol;

import jakarta.validation.constraints.NotBlank;

public record DtoRol(
    @NotBlank (message="el nombre del rol no puede setar vacio")
    String nombre
){

}
