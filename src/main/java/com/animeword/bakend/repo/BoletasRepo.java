package com.animeword.bakend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeword.bakend.models.BoletasModel;
import com.animeword.bakend.models.UserModel;

public interface BoletasRepo extends JpaRepository<BoletasModel,Long> {
    
    List<BoletasModel> findByUser (UserModel user );
}
