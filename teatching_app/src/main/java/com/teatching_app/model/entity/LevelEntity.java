package com.teatching_app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;


    @OneToMany(mappedBy = "level")
    private Set<LessonEntity> lessons;

    public LevelEntity() {
    }
}
