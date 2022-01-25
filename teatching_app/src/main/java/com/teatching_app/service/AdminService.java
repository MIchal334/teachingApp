package com.teatching_app.service;

import com.teatching_app.model.dto.LevelDTO;
import com.teatching_app.model.entity.LevelTemplateEntity;
import com.teatching_app.repository.LevelTemplateRepository;
import com.teatching_app.validator.LevelDataValidator;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final CourseService courseService;
    private final LevelService levelService;
    private final  LessonService lessonService;
    private final LevelDataValidator levelDataValidator;
    private final LevelTemplateRepository levelTemplateRepository;

    public AdminService(CourseService courseService, LevelService levelService, LessonService lessonService, LevelDataValidator levelDataValidator, LevelTemplateRepository levelTemplateRepository) {
        this.courseService = courseService;
        this.levelService = levelService;
        this.lessonService = lessonService;
        this.levelDataValidator = levelDataValidator;
        this.levelTemplateRepository = levelTemplateRepository;
    }

    public LevelTemplateEntity addNewLevel(LevelDTO newLevel) {
        levelDataValidator.validData(newLevel);

        LevelTemplateEntity newLevelTemplate = new LevelTemplateEntity(newLevel);

        return levelTemplateRepository.save(newLevelTemplate);
    }


//    public LessonEntity addNewLesson(Long courseId, Long levelId, LessonDTO newLesson) {
//        LevelEntity level = levelService.getLevelById(levelId);
//        LessonEntity lesson = lessonService.saveNewLesson(new LessonEntity(level));
//        lessonService.addContent(lesson,newLesson.getContent());
//        lessonService.addExercise(lesson,newLesson.getExercise());
//
//        return lesson;
//    }
}
