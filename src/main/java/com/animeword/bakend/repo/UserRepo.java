package com.animeword.bakend.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.animeword.bakend.models.RolModel;
import com.animeword.bakend.models.UserModel;
import java.util.List;


public interface UserRepo extends JpaRepository<UserModel,Long> {
    @Query ("""
        SELECT u FROM UserModel u
            JOIN FETCH u.rol r
            WHERE u.rut = :rut
    """)
    Optional<UserModel> buscarPorRut(@Param ("rut")String rut);

    Optional <UserModel>  findByCorreo(String correo);
    Optional<UserModel> findByRut(String rut);
    List<UserModel> findByRol(RolModel rol);
}

