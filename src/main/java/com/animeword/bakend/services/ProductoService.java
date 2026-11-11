package com.animeword.bakend.services;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.animeword.bakend.error.errorNoencotrado;
import com.animeword.bakend.models.CategoriaModel;
import com.animeword.bakend.models.ProductoModel;
import com.animeword.bakend.repo.CategoriaRepo;
import com.animeword.bakend.repo.ProductosRepo;
import com.animeword.bakend.shared.saredproductos.DtoCategtegoria;
import com.animeword.bakend.shared.saredproductos.DtoProductoEntrada;
import com.animeword.bakend.shared.saredproductos.MapperProducto;

@Service 
public class ProductoService {
    private final ProductosRepo repoProduc;
    private final CategoriaRepo repoCategoria;

    public ProductoService(ProductosRepo repo1,CategoriaRepo repo2){
        this.repoCategoria=repo2;
        this.repoProduc=repo1;
    }

    // Categorias

    public  String createCategori(DtoCategtegoria dto){
        String nCategoria=repoCategoria.save(MapperProducto.dtoTOentitiC(dto)).getNombre();
        return "categoria nueva creada "+nCategoria;
    }

    public  DtoCategtegoria buscar (String ncategoria){
        Long id =repoCategoria.findByNombre(ncategoria).get().getId();
        if (id == null ) {
            throw new errorNoencotrado("la categoria "+ncategoria+" no existe");
        }
        return  new DtoCategtegoria(ncategoria);
    }

    public  DtoCategtegoria Update(String viejo , DtoCategtegoria nuevo){
        CategoriaModel categoriaModel =repoCategoria.findByNombre(viejo).
        orElseThrow(()->new errorNoencotrado("la categoria "+viejo+" no existe"));

        return  
        new DtoCategtegoria
            (repoCategoria.save(MapperProducto.update(categoriaModel.getId(), nuevo)).getNombre());
        
        
    }

    public String delete (String categoria){
        CategoriaModel categoriaM =repoCategoria.findByNombre(categoria).orElseThrow(
            ()->new errorNoencotrado("la categoria "+categoria+" no existe"));
        
        repoCategoria.deleteById(categoriaM.getId());
        return "categoria "+categoria+" ha sido eliminada al igual que si productos";
        
    }

    public  List<DtoCategtegoria> listarC(){
        List<CategoriaModel> list = repoCategoria.findAll();
        List<DtoCategtegoria>list2= new ArrayList<>();
        if (list.size()==0){
            throw new errorNoencotrado("no existen categorias ");
        }
        for (CategoriaModel categoria : list) {
            list2.add(MapperProducto.entityToDto(categoria));
        }
        return  list2;

    }
//Productos
    public List<DtoProductoEntrada> listar (){
        List<ProductoModel> lista= repoProduc.findAll();
        List<DtoProductoEntrada> lis2= new ArrayList<>();
        if(lista.size()==0){
            throw new errorNoencotrado("producos inexistentes");
        }
        for (ProductoModel producto: lista) {
            lis2.add(MapperProducto.entytiToDto(producto));
        }
        return lis2;
    }

    public List<DtoProductoEntrada> listaPorCategoria(String categoria){
        List<DtoProductoEntrada>lista2 = new ArrayList<>();
        List <ProductoModel> lista1= repoProduc.findByCategoriaNombre(categoria);
        if (lista1 == null || lista1.size() == 0){
            throw new errorNoencotrado("La categoria "+categoria+" no existe");
        }
        for (ProductoModel producto : lista1) {
            lista2.add(MapperProducto.entytiToDto(producto));
        }
        return  lista2;
    }

    public DtoProductoEntrada createProducto(DtoProductoEntrada dto){
        CategoriaModel categoria  = repoCategoria.findByNombre(dto.Categoria()).
        orElseThrow(()->new errorNoencotrado("categoria "+dto.Categoria() +" no existe"));
        return MapperProducto.entytiToDto(
            repoProduc.save(MapperProducto.dtoTOentiti(dto, categoria)));
    }

    public DtoProductoEntrada update(DtoProductoEntrada dto){
        ProductoModel produc =repoProduc.findByCodigoBarras(dto.codigoBarras()).
            orElseThrow(()-> new errorNoencotrado("porducto "+dto.nombre()+ " no encontrado"));
        CategoriaModel categoria  = repoCategoria.findByNombre(dto.Categoria()).
            orElseThrow(()->new errorNoencotrado("categoria "+dto.Categoria() +" no existe"));
        return MapperProducto.entytiToDto(repoProduc.save(MapperProducto.update(produc.getId(), dto, categoria)));
        
    }

    public  String  delete(DtoProductoEntrada dto){
        ProductoModel proc =repoProduc.findByCodigoBarras(dto.codigoBarras())
            .orElseThrow(()-> new errorNoencotrado("porducto "+dto.nombre()+ " no encontrado") );
        repoProduc.deleteById(proc.getId()); 
        return "el producto "+dto.nombre()+" fue eliminado con exito uwu";
    }
}