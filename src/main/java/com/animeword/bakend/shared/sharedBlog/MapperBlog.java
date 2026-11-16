package com.animeword.bakend.shared.sharedBlog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.animeword.bakend.models.BlogModel;
import com.animeword.bakend.models.UserModel;
import com.animeword.bakend.shared.shareduserrol.DtoUserEntrada;
import com.animeword.bakend.shared.shareduserrol.MaperUser;

public class MapperBlog {

    public static Map<String,String> contenido(DtoContendio dto){
        Map<String,String> contenido = new HashMap<>();
        contenido.put("tipo", dto.tipo());
        contenido.put("contenido", dto.contenido());
        return contenido;
    }

    public static BlogModel createblog ( List<DtoContendio> contendios,
        DtoBlogEntrada blog, UserModel user ){
        List<Map<String,String>>  contenidoLis = new ArrayList<>();
        for (DtoContendio dtoContendio : contendios) {
            contenidoLis.add( contenido(dtoContendio));
        }
        return  new BlogModel(
            null,
            blog.titulo(),
            blog.fecha(),
            user,
            contenidoLis
        );
    }

    public static BlogModel updateblog ( Long id,List<DtoContendio> contendios,
        DtoBlogEntrada blog, UserModel user ){
        
        List<Map<String,String>>  contenidoLis = new ArrayList<>();
        for (DtoContendio dtoContendio : contendios) {
            contenidoLis.add( contenido(dtoContendio));
        }
        return  new BlogModel(
            id,
            blog.titulo(),
            blog.fecha(),
            user,
            contenidoLis
        );
    }

    public static  DtoBlogSalida salida ( BlogModel entity){
        return new DtoBlogSalida( entity.getTitulo(),
        MaperUser.modelTodtoSalida(entity.getAutor()) ,
        entity.getFecha(), entity.getContenidoJson());
    }

}
