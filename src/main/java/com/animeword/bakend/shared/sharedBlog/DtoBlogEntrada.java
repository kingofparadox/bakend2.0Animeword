package com.animeword.bakend.shared.sharedBlog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoBlogEntrada(
    @NotBlank (message ="el titulo debe tener algo escrito")
    String titulo,
    @NotNull  (message = "debe tener fecha de publicacion")
    LocalDateTime fecha,
    List<Map<String,String>> contenido
) {

}
