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

    @Column(name = "is_finished")
    private Boolean isFinished;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private LessonEntity level;

    @OneToMany(mappedBy = "lesson")
    private Set<LessonContentEntity> lessonContents;

    @OneToMany(mappedBy = "lesson")
    private Set<ExerciseEntity> lessonExercises;
}
