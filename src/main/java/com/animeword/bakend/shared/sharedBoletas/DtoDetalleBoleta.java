package com.animeword.bakend.shared.sharedBoletas;

public record DtoDetalleBoleta(
    String nombreProducto,
    Integer cantidad,
    Integer precioUnitario
) {

}
