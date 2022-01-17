package com.teatching_app.repository;

import com.teatching_app.model.entity.LessonContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonContentRepository extends JpaRepository<LessonContentEntity,Long> {
}
