package com.teatching_app.model.dto;

import com.teatching_app.model.entity.AnswerEntity;
import com.teatching_app.model.entity.ExerciseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ExerciseDTO {

    private String question;
    private Set<String> answers;

    public ExerciseDTO(ExerciseEntity o) {
        this.question = o.getQuestion();
        this.answers = o.getAnswers().stream().map(AnswerEntity::getAnswer).collect(Collectors.toSet());
    }
}
