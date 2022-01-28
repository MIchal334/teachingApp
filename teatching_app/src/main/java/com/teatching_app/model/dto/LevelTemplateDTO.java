package com.teatching_app.model.dto;

import com.teatching_app.model.entity.LevelTemplateEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LevelTemplateDTO {
    private String topic;
    private Integer number;

    public LevelTemplateDTO() {
    }

    public LevelTemplateDTO(LevelTemplateEntity l) {
        this.topic = l.getTopic();
        this.number = l.getNumber();
    }
}
