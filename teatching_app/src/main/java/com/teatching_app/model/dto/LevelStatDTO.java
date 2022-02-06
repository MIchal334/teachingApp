package com.teatching_app.model.dto;

import java.time.LocalDate;
import java.util.List;

public class LevelStatDTO {

    private String topic;
    private float avgScore;
    private int finishedLesson;
    private int countOfLesson;
    private LocalDate DataOfStart;
    private LocalDate DataOfEnd;
    private List<LessonStatDTO> lessonStat;



}
