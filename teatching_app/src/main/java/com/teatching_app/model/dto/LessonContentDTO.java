package com.teatching_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class LessonContentDTO {

    private Integer orderNumber;
    private String advice;
}
