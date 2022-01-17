package com.teatching_app.repository;

import com.teatching_app.model.entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity,Long> {
}
