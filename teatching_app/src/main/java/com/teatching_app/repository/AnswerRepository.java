package com.teatching_app.repository;

import com.teatching_app.model.entity.AnswerEntity;
import com.teatching_app.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {
}
