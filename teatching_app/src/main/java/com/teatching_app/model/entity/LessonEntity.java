package com.teatching_app.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "lessons")
public class LessonEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score")
    private Float score;

    @Column(name = "date_of_start")
    private LocalDate dateOfStart;

    @Column(name = "is_started")
    private Boolean isStarted;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "is_deleted")
    Boolean isDeleted;




    @ManyToOne
    @JoinColumn(name = "level_id")
    private LevelEntity level;

    @ManyToOne
    @JoinColumn(name = "lesson_template_id")
    private LessonTemplateEntity lessonTemplate;


    public LessonEntity( LevelEntity level, LessonTemplateEntity lessonTemplate) {
        this.score = (float)0;
        this.dateOfStart = LocalDate.now();
        this.isStarted = true;
        this.isDeleted = false;
        this.isFinished = false;
        this.level = level;
        this.lessonTemplate = lessonTemplate;
    }

    public LessonEntity() {
    }


}
