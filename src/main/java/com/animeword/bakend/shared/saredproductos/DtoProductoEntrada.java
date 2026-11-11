package com.animeword.bakend.shared.saredproductos;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoProductoEntrada(

    @NotBlank (message = "nombre de prodcuto no puede estar vacio")
    String nombre,
    @NotBlank (message = "codigo de barras no puede estar en blanco")
    String codigoBarras,
    @NotNull (message = "el precio debe tener un valor")
    @Min (value = 1000,message = "los productos deben tener un valor de mil peosos minimo")
    Integer precio,
    @Min (value = 500,message = "la oferta minima son 500 pesos")
    Integer oferta,
    @NotNull (message = "el Stok debe tener un valor")
    @Min (value = 1,message = "para agregra unporducto el stock minimo es 1")
    Integer Stok,
    @NotBlank (message = "cada procucto deve tener al menos una imagen")
    List<String> path,
    @NotNull (message = "el producto debe tener una categoria")
    String Categoria

) {

}
