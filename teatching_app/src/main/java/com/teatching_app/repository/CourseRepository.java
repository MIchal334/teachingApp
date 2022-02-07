package com.teatching_app.repository;

import com.teatching_app.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {


    @Query("from CourseEntity as c where c.user.id = :userId and c.subject = :sub")
    Optional<CourseEntity> findAllByUserIdAndSubject(@Param("userId") Long id,@Param("sub") String subject);

}
