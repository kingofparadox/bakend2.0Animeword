package com.animeword.bakend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeword.bakend.models.IncidentesModel;
import com.animeword.bakend.models.UserModel;

import java.time.LocalDate;
import java.util.List;


public interface IncidentesRepo extends JpaRepository<IncidentesModel,Long> {
    
    List<IncidentesModel> findByUser(UserModel user);
    List<IncidentesModel> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
