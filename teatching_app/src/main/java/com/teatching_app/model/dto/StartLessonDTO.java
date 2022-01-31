package com.teatching_app.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class StartLessonDTO {

    private LevelTemplateDTO level;
    private LessonTemplateDTO lesson;
    private Long currentLevelId;
    private Long currentLessonId;
    private Integer lessonTemplateNumber;

    public StartLessonDTO() {
    }

    public StartLessonDTO(LevelTemplateDTO level, LessonTemplateDTO lesson, Long currentLevelId, Long currentLessonId,Integer lessonTemplateNumber) {
        this.level = level;
        this.lesson = lesson;
        this.currentLevelId = currentLevelId;
        this.currentLessonId = currentLessonId;
        this.lessonTemplateNumber = lessonTemplateNumber;
    }
}
