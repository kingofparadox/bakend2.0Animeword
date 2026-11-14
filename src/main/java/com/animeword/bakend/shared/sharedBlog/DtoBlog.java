package com.animeword.bakend.shared.sharedBlog;

import java.time.LocalDateTime;

import com.animeword.bakend.shared.shareduserrol.DtoUserSalida;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoBlog(
    @NotBlank (message ="el titulo debe tener algo escrito")
    String titulo,
    DtoUserSalida autor,
    @NotNull (message = "debe tener fecha de publicacion")
    LocalDateTime fecha
) {

}
