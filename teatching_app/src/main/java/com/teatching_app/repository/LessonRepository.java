package com.teatching_app.repository;

import com.teatching_app.model.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity,Long> {
    @Query("from LessonEntity  as l  where  l.level.id = :levelId")
    List<LessonEntity> findAllByLevelId(@Param("levelId") Long id);

    @Query("from LessonEntity  as l  where l.lessonTemplate.number = :number and l.level.course.user.id = :id and  l.isFinished = true ")
    List<LessonEntity> findFinishedLessonByStudentIdAndLevelNumber(@Param("id") long studentId,@Param("number") long lessonNumber);

}
