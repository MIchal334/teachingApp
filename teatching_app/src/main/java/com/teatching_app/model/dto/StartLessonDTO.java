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
    private Integer currentLevel;
    private Integer currentLesson;

    public StartLessonDTO() {
    }

    public StartLessonDTO(LevelTemplateDTO level, LessonTemplateDTO lesson, Integer currentLevel, Integer currentLesson) {
        this.level = level;
        this.lesson = lesson;
        this.currentLevel = currentLevel;
        this.currentLesson = currentLesson;
    }
}
