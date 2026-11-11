package com.animeword.bakend.shared.saredproductos;

import com.animeword.bakend.models.CategoriaModel;
import com.animeword.bakend.models.ProductoModel;

public class MapperProducto {

    public static ProductoModel dtoTOentiti(DtoProductoEntrada dto,CategoriaModel categoria ){
        return  new ProductoModel(
            null,
            dto.nombre(),
            dto.codigoBarras(),
            dto.precio(),
            dto.Stok(),
            dto.oferta(),
            dto.path(),
            categoria
        );
    } 

    public static DtoProductoEntrada entytiToDto(ProductoModel entity){
        return  new DtoProductoEntrada(entity.getNombre(),
            entity.getCodigoBarras(),
            entity.getPrecio(), 
            entity.getOferta(),
            entity.getStock(),
            entity.getImagenPaths(),
            entity.getCategoria().getNombre());
    }

    public static ProductoModel update(Long id,DtoProductoEntrada dto,CategoriaModel categoria ){
        return  new ProductoModel(
            id,
            dto.nombre(),
            dto.codigoBarras(),
            dto.precio(),
            dto.Stok(),
            dto.oferta(),
            dto.path(),
            categoria
        );
    } 

    public static CategoriaModel dtoTOentitiC(DtoCategtegoria dto ){
        return  new CategoriaModel(
            null, dto.nombre());
    } 


        public static CategoriaModel update(Long id,DtoCategtegoria dto ){
        return  new CategoriaModel(
            id, dto.nombre());
    } 

    public static DtoCategtegoria entityToDto(CategoriaModel entity ){
        return new DtoCategtegoria(entity.getNombre()) ;
    }
    
    
}
