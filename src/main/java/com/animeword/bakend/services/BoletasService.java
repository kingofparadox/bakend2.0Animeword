package com.animeword.bakend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.animeword.bakend.error.errorNoencotrado;
import com.animeword.bakend.models.BoletasModel;
import com.animeword.bakend.models.ProductoModel;
import com.animeword.bakend.models.UserModel;
import com.animeword.bakend.repo.BoletasRepo;
import com.animeword.bakend.repo.ProductosRepo;
import com.animeword.bakend.repo.UserRepo;
import com.animeword.bakend.shared.saredproductos.DtoProductoPedido;
import com.animeword.bakend.shared.sharedBoletas.DtoBoletas;
import com.animeword.bakend.shared.sharedBoletas.DtoDetalleBoleta;
import com.animeword.bakend.shared.sharedBoletas.MapperBolea;

import jakarta.transaction.Transactional;

@Service 
public class BoletasService {
    private final ProductosRepo repoProductos;
    private final BoletasRepo repoBoletas;
    private final UserRepo repoUser;

    public BoletasService(ProductosRepo repo2, BoletasRepo repo1, UserRepo repo3){
        this.repoBoletas = repo1;
        this.repoProductos= repo2;
        this.repoUser=repo3;
    }

    @Transactional 
    public DtoBoletas create (String rut, List<DtoProductoPedido> productos ){
        UserModel usuario =repoUser.buscarPorRut(rut).orElseThrow(
            ()-> new errorNoencotrado("no existe usaurio con el rut "+rut));
        List<ProductoModel> productoActalizado= new ArrayList<>();
        List<DtoDetalleBoleta>detallesB=new ArrayList<>();
        for (DtoProductoPedido item : productos) {
            ProductoModel producto = repoProductos.findByNombre(item.nameprodcuto());
            if (producto == null) {
                throw new errorNoencotrado(" no existe prodcucto con el nombre "+item.nameprodcuto());
            }
            if (producto.getStock()<item.cantidad() ){
                    throw new errorNoencotrado("solo quedan "+ producto.getStock()
                    + "por eso no se puede completar la venta");
            }
            detallesB.add(MapperBolea.detalleProducto(producto,item.cantidad()));
            producto.setStock(producto.getStock()-item.cantidad());
            productoActalizado.add(producto);
        }
        repoProductos.saveAll(productoActalizado);
        DtoBoletas boletaF =new DtoBoletas(null, usuario,  LocalDateTime.now(), null, null);
        return  MapperBolea.salida(repoBoletas.save(MapperBolea.createBoleta(boletaF, detallesB)));
    }

    public  List<DtoBoletas> boletas (){
        List<DtoBoletas>lista =new ArrayList<>();
        List <BoletasModel>listaBoletas = repoBoletas.findAll();
        if (listaBoletas.size()<=0){
            throw new errorNoencotrado("mijo no existen boletas no hemos vendido nada twt");
        }
        for (BoletasModel dtoBoletas : listaBoletas ) {
            lista.add(MapperBolea.salida(dtoBoletas));
        }
        return lista;
    }

    public List<DtoBoletas> boletasUser(String rut){
        UserModel user =repoUser.buscarPorRut(rut).orElseThrow(() -> new errorNoencotrado("usuario de rut "+rut+ " no registrado"));
        List<BoletasModel> boletas  =repoBoletas.findByUser(user);
        List<DtoBoletas> boletasDto = new ArrayList<>();
        if(boletas.size()==0){
            throw new errorNoencotrado("no tienes compras realiazadas");
        }
        for (BoletasModel item : boletas) {
            boletasDto.add(MapperBolea.salida(item));
        }
        return  boletasDto;
    }

    public String Deleteboleta (Long id){
        repoBoletas.deleteById(id);
        return "boleta con id"+id+" eliminada con exito";
    }
}
