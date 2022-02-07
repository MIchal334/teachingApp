package com.teatching_app.model.dto;

import com.teatching_app.model.entity.LessonEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LessonStatDTO {

    private String lessonTopic;
    private float lessonScore;

    public LessonStatDTO() {
    }

    public LessonStatDTO(LessonEntity lesson) {
        this.lessonTopic = lesson.getLessonTemplate().getTopic();
        this.lessonScore = lesson.getScore();
    }
}
