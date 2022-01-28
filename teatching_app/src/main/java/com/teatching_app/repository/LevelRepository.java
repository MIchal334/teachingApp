package com.teatching_app.repository;

import com.teatching_app.model.entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity,Long> {

    @Query("from LevelEntity as l where  l.course.id = :courseId")
    List<LevelEntity> findByCourseId(@Param("courseId") Long id);

}
