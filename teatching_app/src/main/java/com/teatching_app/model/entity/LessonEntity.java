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

    @OneToMany(mappedBy = "lesson")
    private Set<LessonContentEntity> lessonContents;

    @OneToMany(mappedBy = "lesson")
    private Set<ExerciseEntity> lessonExercises;

    public LessonEntity(LevelEntity level) {
        this.level = level;
    }

    public LessonEntity() {
    }
}
