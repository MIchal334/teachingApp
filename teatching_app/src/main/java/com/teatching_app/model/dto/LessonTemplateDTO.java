package com.teatching_app.model.dto;

import com.teatching_app.model.entity.ExerciseEntity;
import com.teatching_app.model.entity.LessonContentEntity;
import com.teatching_app.model.entity.LessonTemplateEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class LessonTemplateDTO {

    private String topic;
    private Integer number;
    Set<LessonContentDTO> content;
    Set<ExerciseDTO> exercise;

    public LessonTemplateDTO(Set<LessonContentDTO> content, Set<ExerciseDTO> exercise) {
        this.content = content;
        this.exercise = exercise;
    }

    public LessonTemplateDTO() {
    }

    public LessonTemplateDTO(LessonTemplateEntity l) {
        this.topic = l.getTopic();
        this.number = l.getNumber();
        this.content = l.getLessonContents().stream()
                .filter(o -> !o.getIsDeleted())
                .map(LessonContentDTO::new)
                .collect(Collectors.toSet());
        this.exercise = l.getLessonExercises().stream()
                .filter((o-> !o.getIsDeleted()))
                .map(ExerciseDTO::new)
                .collect(Collectors.toSet());

    }
}
