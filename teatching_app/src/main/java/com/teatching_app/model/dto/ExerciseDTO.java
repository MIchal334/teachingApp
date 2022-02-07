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
    private String correct;
    private Set<String> answers;

    public ExerciseDTO() {
    }

    public ExerciseDTO(ExerciseEntity o) {
        this.question = o.getQuestion();
        this.correct = o.getCorrectAnswer();
        this.answers = o.getAnswers().stream()
                .filter(ans -> !ans.getIsDeleted())
                .map(AnswerEntity::getAnswer)
                .collect(Collectors.toSet());
    }
}
