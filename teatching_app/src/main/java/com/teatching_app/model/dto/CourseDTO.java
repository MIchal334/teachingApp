package com.teatching_app.model.dto;

import com.teatching_app.model.entity.CourseEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
public class CourseDTO  {

    private String studentFullName;
    private String subject ;
    private float levelOfCompletion ;
    private LocalDate dateOfStart;

    public CourseDTO() {
    }

    public CourseDTO(CourseEntity courseEntity) {
        this.studentFullName = courseEntity.getUser().getName() + " "+ courseEntity.getUser().getSurname();
        this.subject = courseEntity.getSubject();
        this.levelOfCompletion = courseEntity.getLevelOfCompletion();
        this.dateOfStart = courseEntity.getDateOfStart();
    }
}

