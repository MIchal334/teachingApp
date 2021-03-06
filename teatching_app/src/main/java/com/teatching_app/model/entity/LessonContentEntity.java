package com.teatching_app.model.entity;

import com.teatching_app.model.dto.LessonContentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lesson_contents")
public class LessonContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "advice")
    private String advice;

    @Column(name = "is_deleted")
    Boolean isDeleted;


    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonTemplateEntity lesson;

    public LessonContentEntity() {
    }


    public LessonContentEntity(LessonTemplateEntity lesson, LessonContentDTO c) {
        this.orderNumber = c.getOrderNumber();
        this.advice = c.getAdvice();
        this.lesson = lesson;
        this.isDeleted = false;
    }
}
