package com.teatching_app.model;

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
}
