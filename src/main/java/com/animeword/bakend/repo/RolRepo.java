package com.animeword.bakend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeword.bakend.models.RolModel;



public interface RolRepo extends  JpaRepository<RolModel, Long> {
    Optional<RolModel>  findByNombre(String nombre);
}
