package com.teatching_app.model.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class StatDTO {

        private String courseSubject;
        private LocalDate DataOfStart;
        private LocalDate DataOfEnd;
        private Float levelOfCompletion;
        private List<LevelStatDTO> levelStatDTO;



}
