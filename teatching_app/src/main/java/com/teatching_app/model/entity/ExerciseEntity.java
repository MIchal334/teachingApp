package com.teatching_app.model.entity;

import com.teatching_app.model.dto.ExerciseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


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


    @OneToMany(mappedBy = "exercise")
    private Set<AnswerEntity> answers;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonTemplateEntity lesson;

    public ExerciseEntity() {
    }

    public ExerciseEntity(LessonTemplateEntity lesson, ExerciseDTO exercise) {
        this.question = exercise.getQuestion();
        this.lesson = lesson;
    }
}
