package com.teatching_app.model.dto;

import com.teatching_app.model.entity.AnswerEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Set;

@Getter
@Setter
public class ExerciseDTO {

    private String question;
    private Set<String> answers;

}
