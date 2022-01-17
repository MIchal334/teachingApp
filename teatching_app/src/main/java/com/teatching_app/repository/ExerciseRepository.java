package com.teatching_app.repository;

import com.teatching_app.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity,Long> {
}
