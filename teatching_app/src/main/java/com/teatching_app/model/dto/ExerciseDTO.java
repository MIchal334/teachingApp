package com.teatching_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ExerciseDTO {

    private String question;
    private String answer;

}
