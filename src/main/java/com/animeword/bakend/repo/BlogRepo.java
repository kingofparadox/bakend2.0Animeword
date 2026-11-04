package com.animeword.bakend.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.animeword.bakend.models.BlogModel;
import com.animeword.bakend.models.UserModel;

public interface BlogRepo extends JpaRepository<BlogModel,Long> {

    List<BlogModel> findByAutor(UserModel autor);
}