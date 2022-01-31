package com.teatching_app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "levels")
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "average_score")
    private Float averageScore;

    @Column(name = "level_of_completion")
    private Float levelOfCompletion;

    @Column(name = "date_of_start")
    private LocalDate dateOfStart;

    @Column(name = "date_of_end")
    private LocalDate dateOfEnd;

    @Column(name = "is_started")
    private Boolean isStarted;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "is_deleted")
    Boolean isDeleted;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "level_template_id")
    private LevelTemplateEntity levelTemplate;

    @OneToMany(mappedBy = "level")
    private Set<LessonEntity> lessons;

    public LevelEntity() {
    }

    public LevelEntity( CourseEntity course, LevelTemplateEntity levelTemplate) {
        this.averageScore = (float)0;
        this.levelOfCompletion = (float)0;
        this.dateOfStart = LocalDate.now();
        this.isStarted = true;
        this.isFinished = false;
        this.isDeleted = false;
        this.course = course;
        this.levelTemplate = levelTemplate;
    }


}
