package com.teatching_app.model.dto;

import com.teatching_app.model.entity.CourseEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class StatDTO {

        private String courseSubject;
        private LocalDate DataOfStart;
        private LocalDate DataOfEnd;
        private Float levelOfCompletion;
        private Float avgScore;
        private List<LevelStatDTO> levelStatDTO;

        public StatDTO() {
        }

        public StatDTO(CourseEntity courseEntity, List<LevelStatDTO> listOfLevelStat) {
                this.courseSubject = courseEntity.getSubject();
                this.avgScore = courseEntity.getAverageScore();
                this.DataOfStart = courseEntity.getDateOfStart();
                this.DataOfEnd = courseEntity.getDateOfEnd();
                this.levelOfCompletion = courseEntity.getLevelOfCompletion();
                this.levelStatDTO = listOfLevelStat;
        }
}
