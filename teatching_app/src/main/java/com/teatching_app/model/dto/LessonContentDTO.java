package com.teatching_app.model.dto;

import com.teatching_app.model.entity.LessonContentEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class LessonContentDTO {

    private Integer orderNumber;
    private String advice;

    public LessonContentDTO() {
    }

    public LessonContentDTO(LessonContentEntity o) {
        this.orderNumber = o.getOrderNumber();
        this.advice = o.getAdvice();
    }
}
