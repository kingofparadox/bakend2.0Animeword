package com.animeword.bakend.shared.sharedBlog;

import jakarta.validation.constraints.NotBlank;

public record DtoContendio(
    @NotBlank (message = "el tipo no puede estar vacio")
    String tipo,
    @NotBlank (message = "el contenido no puede estar vacio")
    String contenido
) {}
