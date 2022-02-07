package com.teatching_app.model.dto;

import com.teatching_app.model.entity.LevelTemplateEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LevelTemplateDTO {
    private Long id;
    private String topic;
    private Integer number;

    public LevelTemplateDTO() {
    }

    public LevelTemplateDTO(LevelTemplateEntity l) {
        this.id = l.getId();
        this.topic = l.getTopic();
        this.number = l.getNumber();
    }
}
