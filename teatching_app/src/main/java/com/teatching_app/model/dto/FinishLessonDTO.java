package com.teatching_app.model.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FinishLessonDTO {
    private Float score;
    private Long currentLevelId;
    private Long currentLessonId;
    private Integer lessonTemplateNumber;

}
