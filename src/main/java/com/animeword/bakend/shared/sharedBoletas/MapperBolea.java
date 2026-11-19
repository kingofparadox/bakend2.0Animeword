package com.animeword.bakend.shared.sharedBoletas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.animeword.bakend.models.BoletasModel;
import com.animeword.bakend.models.ProductoModel;

public class MapperBolea {
    
    public static DtoDetalleBoleta detalleProducto(ProductoModel entity, Integer cantidad ){
        return new DtoDetalleBoleta(entity.getNombre()
        ,cantidad,
        entity.getPrecio());
    }
    
    public static  Map<String,Object> dtoToJson(DtoDetalleBoleta dto){
        Map<String, Object> productoDetalle= new HashMap<>();
        productoDetalle.put("producto", dto.nombreProducto());
        productoDetalle.put("cantidad", dto.cantidad());
        productoDetalle.put("precio Unitario", dto.precioUnitario());
        return  productoDetalle;
    }

    public static BoletasModel createBoleta(DtoBoletas dto, List<DtoDetalleBoleta> dtoDetalles){
        List<Map<String,Object>> detalles = new ArrayList<>();
        Integer subtotal = 0;
        for (DtoDetalleBoleta dtoDetalleBoleta : dtoDetalles) {
            detalles.add(dtoToJson(dtoDetalleBoleta));
            subtotal = (dtoDetalleBoleta.cantidad() *
            dtoDetalleBoleta.precioUnitario() )+subtotal;
        }
        return  new BoletasModel(null,
            dto.usuario(),subtotal,
            dto.fecha(),detalles);
    }

    public static  DtoBoletas salida(BoletasModel entity){
        return new DtoBoletas(entity.getDetalles(),
            entity.getUser(), 
            entity.getFecha(), 
            entity.getTotal(), 
            entity.getId());
    }
}
