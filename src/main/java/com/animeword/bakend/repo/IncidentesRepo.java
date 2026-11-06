package com.animeword.bakend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.animeword.bakend.models.IncidentesModel;
import com.animeword.bakend.models.UserModel;
import java.time.LocalDateTime;
import java.util.List;


public interface IncidentesRepo extends JpaRepository<IncidentesModel,Long> {
    
    List<IncidentesModel> findByUser(UserModel user);
    
    @Query("SELECT i FROM IncidentesModel i WHERE i.fechaInicio BETWEEN :inicio AND :fin")
    List<IncidentesModel> buscarPorRangoDeFechas(
        @Param("inicio") LocalDateTime inicio, 
        @Param("fin") LocalDateTime fin
);


}
