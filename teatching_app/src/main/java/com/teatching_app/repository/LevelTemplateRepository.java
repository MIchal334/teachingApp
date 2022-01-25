package com.teatching_app.repository;

import com.teatching_app.model.entity.LevelEntity;
import com.teatching_app.model.entity.LevelTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelTemplateRepository extends JpaRepository<LevelTemplateEntity,Long> {
    @Query("select lt.number from LevelTemplateEntity as lt")
    List<Integer> findOfLevelNumbers();

    @Query("select lt.topic from LevelTemplateEntity as lt")
    List<String> findTopicsOfLevels();
}
