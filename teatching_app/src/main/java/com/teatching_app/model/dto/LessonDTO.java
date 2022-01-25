package com.teatching_app.model.dto;

import com.teatching_app.model.entity.ExerciseEntity;
import com.teatching_app.model.entity.LessonContentEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class LessonDTO {

    private String topic;
    private Integer number;
    Set<LessonContentDTO> content;
    Set<ExerciseDTO> exercise;

    public LessonDTO(Set<LessonContentDTO> content, Set<ExerciseDTO> exercise) {
        this.content = content;
        this.exercise = exercise;
    }
}
