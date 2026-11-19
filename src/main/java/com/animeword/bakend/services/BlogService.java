package com.animeword.bakend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.animeword.bakend.error.errorNoencotrado;
import com.animeword.bakend.models.BlogModel;
import com.animeword.bakend.models.UserModel;
import com.animeword.bakend.repo.BlogRepo;
import com.animeword.bakend.repo.UserRepo;
import com.animeword.bakend.shared.sharedBlog.DtoBlogEntrada;
import com.animeword.bakend.shared.sharedBlog.DtoBlogSalida;
import com.animeword.bakend.shared.sharedBlog.DtoContendio;
import com.animeword.bakend.shared.sharedBlog.MapperBlog;

@Service 

public class BlogService  {

    private final  BlogRepo repoBlog;
    private final UserRepo repoUser;


    public BlogService(BlogRepo rep1, UserRepo rep2){
        this.repoBlog= rep1;
        this.repoUser= rep2;
    
    }


    public List<DtoBlogSalida>  Blog(){
        List<BlogModel>lista=repoBlog.findAll();
        List<DtoBlogSalida>listaf=new ArrayList<>();
            if (lista.size()>0){
                for (BlogModel entity : lista) {
                    listaf.add(MapperBlog.salida(entity));
                }
            return  listaf;
            }
        throw new errorNoencotrado("no tenemos blog registrados a llorar pal momte");
    }

    public DtoBlogSalida create(DtoBlogEntrada dto, String rut, List<DtoContendio> contendio ) {
        UserModel user = repoUser.findByRut(rut).orElseThrow(()
        -> new errorNoencotrado("rut "+rut+" no encontrado"));
        return MapperBlog.salida(repoBlog.save(MapperBlog.createblog(contendio, dto, user)));
    }

    public DtoBlogSalida update(DtoBlogEntrada dto, List<DtoContendio>contendio ){
        BlogModel Blog = repoBlog.findByTitulo(dto.titulo());
        if (Blog== null){
            throw new errorNoencotrado("Blog no encontrado no modificar el titulo");
        }
        return MapperBlog.salida(repoBlog.save(MapperBlog.updateblog(Blog.getId(), contendio, dto, Blog.getAutor())));
    }

    public String delete (String titulo){
        BlogModel blog = repoBlog.findByTitulo(titulo);
        if (blog == null){
            throw new errorNoencotrado("no existe blog con el titulo "+ titulo );
        }
        repoBlog.deleteById(blog.getId());
        return "fue eliminado el blog  con titulo "+ blog.getTitulo();
    }
}
