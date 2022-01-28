package com.teatching_app.model.entity;


import com.teatching_app.model.dto.LessonDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "answer")
public class AnswerEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer")
    private String answer;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "exe_id")
    private ExerciseEntity exercise;


    public AnswerEntity() {
    }

    public AnswerEntity(String answer, ExerciseEntity  exercise) {
        this.exercise = exercise;
        this.answer = answer;
    }



}
