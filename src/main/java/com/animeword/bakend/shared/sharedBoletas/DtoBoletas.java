package com.animeword.bakend.shared.sharedBoletas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import com.animeword.bakend.models.UserModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DtoBoletas(
    List<Map<String,Object>> productos,
    @NotNull (message = "denbe tener un usuario")
    UserModel usuario,
    @NotNull (message = "fecha obligatoria")
    LocalDateTime fecha,
    @Min (value = 500, message = " ningun producto puede valer menos de 500 pesos" )
    Integer total,
    @NotNull (message = "id de boleta no puede ser nulo")
    Long id 
) {

}
