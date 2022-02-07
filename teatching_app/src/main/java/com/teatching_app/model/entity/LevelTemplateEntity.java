package com.teatching_app.model.entity;

import com.teatching_app.model.dto.LevelTemplateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "level_template")
public class LevelTemplateEntity  implements Comparator<LevelTemplateEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "number")
    private int number;

    @Column(name = "is_deleted")
    Boolean isDeleted;


    @OneToMany(mappedBy = "levelTemplate")
    private Set<LessonTemplateEntity> lessonsTemplate;

    @OneToMany(mappedBy = "levelTemplate")
    private Set<LevelEntity> levels;

    public LevelTemplateEntity() {
    }

    public LevelTemplateEntity(LevelTemplateDTO newLevel) {
        this.number = newLevel.getNumber();
        this.topic = newLevel.getTopic();
        this.isDeleted = false;
    }

    @Override
    public int compare(LevelTemplateEntity l1, LevelTemplateEntity l2) {
        return Integer.compare(l1.getNumber(), l2.getNumber());
    }
}
