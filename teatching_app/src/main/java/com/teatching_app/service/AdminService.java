package com.teatching_app.service;

import com.teatching_app.model.dto.LessonDTO;
import com.teatching_app.model.entity.LessonEntity;
import com.teatching_app.model.entity.LevelEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final CourseService courseService;
    private final LevelService levelService;
    private final  LessonService lessonService;

    public AdminService(CourseService courseService, LevelService levelService, LessonService lessonService) {
        this.courseService = courseService;
        this.levelService = levelService;
        this.lessonService = lessonService;
    }

    public LessonEntity addNewLesson(Long courseId, Long levelId, LessonDTO newLesson) {
        LevelEntity level = levelService.getLevelById(levelId);
        LessonEntity lesson = lessonService.saveNewLesson(new LessonEntity(level));
        lessonService.addContent(lesson,newLesson.getContent());
        lessonService.addExercise(lesson,newLesson.getExercise());

        return lesson;
    }
}
