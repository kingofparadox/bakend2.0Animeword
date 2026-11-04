package com.animeword.bakend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.animeword.bakend.models.ProductoModel;
import java.util.List;
import java.util.Optional;


public interface ProductosRepo extends JpaRepository<ProductoModel, Long> {

    @Query("SELECT p FROM ProductoModel p JOIN p.categoria c WHERE c.nombre = :nombreCategoria")
    List<ProductoModel> findByCategoriaNombre(@Param("nombreCategoria") String nombreCategoria);
    Optional<ProductoModel>  findByCodigoBarras(String codigoBarras);

}
