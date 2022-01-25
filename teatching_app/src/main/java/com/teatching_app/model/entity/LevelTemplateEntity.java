package com.teatching_app.model.entity;

import com.teatching_app.model.dto.LevelDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "level_template")
public class LevelTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "number")
    private int number;


    @OneToMany(mappedBy = "levelTemplate")
    private Set<LessonTemplateEntity> lessonsTemplate;

    public LevelTemplateEntity() {
    }

    public LevelTemplateEntity(LevelDTO newLevel) {
        this.number = newLevel.getNumber();
        this.topic = newLevel.getTopic();
    }
}
