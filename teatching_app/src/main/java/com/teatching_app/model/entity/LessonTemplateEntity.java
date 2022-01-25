package com.teatching_app.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "lesson_template")
public class LessonTemplateEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "number")
    private int number;


    @ManyToOne
    @JoinColumn(name = "level_template_id")
    private LevelTemplateEntity levelTemplate;

    @OneToMany(mappedBy = "lesson")
    private Set<LessonContentEntity> lessonContents;

    @OneToMany(mappedBy = "lesson")
    private Set<ExerciseEntity> lessonExercises;


    public LessonTemplateEntity() {
    }
}
