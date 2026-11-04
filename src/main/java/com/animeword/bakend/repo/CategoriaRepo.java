package com.animeword.bakend.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.animeword.bakend.models.CategoriaModel;



public interface CategoriaRepo extends JpaRepository<CategoriaModel, Long> {

    Optional<CategoriaModel>  findByNombre(String nombre);



    
} 