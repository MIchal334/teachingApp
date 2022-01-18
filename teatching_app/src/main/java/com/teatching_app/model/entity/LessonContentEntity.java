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

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    public LessonContentEntity() {
    }

    public LessonContentEntity(LessonEntity lesson, LessonContentDTO content) {
        this.lesson = lesson;
        this.orderNumber = content.getOrderNumber();
        this.advice = content.getAdvice();
    }
}
