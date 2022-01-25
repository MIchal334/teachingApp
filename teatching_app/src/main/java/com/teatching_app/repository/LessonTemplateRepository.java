package com.teatching_app.repository;

import com.teatching_app.model.entity.LessonTemplateEntity;
import com.teatching_app.model.entity.LevelTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonTemplateRepository extends JpaRepository<LessonTemplateEntity,Long> {

    @Query("select lt.number from LessonTemplateEntity as lt where lt.levelTemplate.id = :id")
    List<Integer> findOfLessonNumbers(@Param("id") Long levelId);

    @Query("select lt.topic from LessonTemplateEntity as lt where lt.levelTemplate.id = :id")
    List<String> findTopicsOfLesson(@Param("id") Long levelId);
}
