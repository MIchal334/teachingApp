package com.teatching_app.repository;

import com.teatching_app.model.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity,Long> {
}
