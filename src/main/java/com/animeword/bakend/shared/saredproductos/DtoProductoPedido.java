package com.animeword.bakend.shared.saredproductos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoProductoPedido(
    @NotBlank (message ="debe poner el nombre del producto")
    @NotNull (message = "el producto debe tener un nombre")
    String nameprodcuto,
    @Min (value = 1, message = "minimo es un solo prodcucto")
    Integer cantidad
) {
    
}
