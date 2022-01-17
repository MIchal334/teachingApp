package com.teatching_app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "exercises")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;
}
