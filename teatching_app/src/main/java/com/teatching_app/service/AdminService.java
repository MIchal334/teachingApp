package com.teatching_app.service;

import com.teatching_app.model.dto.LessonDTO;
import com.teatching_app.model.dto.LevelDTO;
import com.teatching_app.model.entity.LessonTemplateEntity;
import com.teatching_app.model.entity.LevelTemplateEntity;
import com.teatching_app.repository.LevelTemplateRepository;
import com.teatching_app.validator.LessonDataValidator;
import com.teatching_app.validator.LevelDataValidator;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final LevelService levelService;
    private final LessonService lessonService;
    private final LessonDataValidator lessonDataValidator;
    private final LevelDataValidator levelDataValidator;
    private final LevelTemplateRepository levelTemplateRepository;

    public AdminService(LevelService levelService, LessonService lessonService, LessonDataValidator lessonDataValidator, LevelDataValidator levelDataValidator, LevelTemplateRepository levelTemplateRepository) {

        this.levelService = levelService;
        this.lessonService = lessonService;
        this.lessonDataValidator = lessonDataValidator;
        this.levelDataValidator = levelDataValidator;
        this.levelTemplateRepository = levelTemplateRepository;
    }

    public LevelTemplateEntity addNewLevel(LevelDTO newLevel) {
        levelDataValidator.validData(newLevel);

        LevelTemplateEntity newLevelTemplate = new LevelTemplateEntity(newLevel);

        return levelTemplateRepository.save(newLevelTemplate);
    }


    public LessonTemplateEntity addNewLesson(Long levelId, LessonDTO newLesson) {
        lessonDataValidator.validData(newLesson, levelId);

        LevelTemplateEntity levelTemplate = levelService.getLevelTemplateById(levelId);
        LessonTemplateEntity lessonTemplate = lessonService.saveNewLessonTemplate(newLesson,levelTemplate);
        lessonService.addContent(lessonTemplate,newLesson.getContent());
        lessonService.addExercise(lessonTemplate,newLesson.getExercise());

        return lessonTemplate;
    }
}
