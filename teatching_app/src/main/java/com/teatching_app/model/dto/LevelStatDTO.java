package com.teatching_app.model.dto;

import com.teatching_app.model.entity.LevelEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class LevelStatDTO {

    private String topic;
    private float avgScore;
    private int finishedLesson;
    private int countOfLessonInLevel;
    private LocalDate DataOfStart;
    private LocalDate DataOfEnd;
    private Float levelOfCompletion;
    private List<LessonStatDTO> lessonStat;

    public LevelStatDTO() {
    }

    public LevelStatDTO(LevelEntity level, List<LessonStatDTO> lessonStat) {
        this.topic = level.getLevelTemplate().getTopic();
        this.avgScore = level.getAverageScore();
        this.DataOfStart = level.getDateOfStart();
        this.DataOfEnd = level.getDateOfEnd();
        this.lessonStat = lessonStat;
        this.countOfLessonInLevel = level.getLevelTemplate().getLessonsTemplate().size();
        this.finishedLesson = lessonStat.size();
        this.levelOfCompletion = level.getLevelOfCompletion();
    }
}
