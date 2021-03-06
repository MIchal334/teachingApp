package com.teatching_app.model.entity;


import com.teatching_app.model.dto.LessonTemplateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "lesson_template")
public class LessonTemplateEntity  implements Comparator<LessonTemplateEntity> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "number")
    private int number;

    @Column(name = "is_deleted")
    Boolean isDeleted;



    @ManyToOne
    @JoinColumn(name = "level_template_id")
    private LevelTemplateEntity levelTemplate;

    @OneToMany(mappedBy = "lesson")
    private Set<LessonContentEntity> lessonContents;

    @OneToMany(mappedBy = "lesson")
    private Set<ExerciseEntity> lessonExercises;

    @OneToMany(mappedBy = "lessonTemplate")
    private Set<LessonEntity> lessons;


    public LessonTemplateEntity() {
    }

    public LessonTemplateEntity(LessonTemplateDTO newLesson, LevelTemplateEntity levelTemplate) {
        this.number = newLesson.getNumber();
        this.topic = newLesson.getTopic();
        this.levelTemplate = levelTemplate;
        this.isDeleted = false;
    }



    @Override
    public int compare(LessonTemplateEntity l1, LessonTemplateEntity l2) {
        return Integer.compare(l1.getNumber(), l2.getNumber());
    }
}
