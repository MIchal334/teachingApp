package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.dto.LevelTemplateDTO;
import com.teatching_app.model.entity.CourseEntity;
import com.teatching_app.model.entity.LessonTemplateEntity;
import com.teatching_app.model.entity.LevelEntity;
import com.teatching_app.model.entity.LevelTemplateEntity;
import com.teatching_app.repository.LevelRepository;
import com.teatching_app.repository.LevelTemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final LevelTemplateRepository levelTemplateRepository;
    private final LessonService lessonService;


    public LevelService(LevelRepository courseRepository, LevelTemplateRepository levelTemplateRepository, LessonService lessonService) {
        this.levelRepository = courseRepository;
        this.levelTemplateRepository = levelTemplateRepository;
        this.lessonService = lessonService;

    }

    public LevelTemplateEntity getLevelTemplateById(Long id){
        return  levelTemplateRepository.findById(id)
                .filter(l -> !l.getIsDeleted())
                .orElseThrow(() -> new ResourceNotExistsException("Level template not exist"));
    }

    public void  deleteLevelTemplateById(Long levelId){
        LevelTemplateEntity levelToDelete = getLevelTemplateById(levelId);
        levelToDelete.getLessonsTemplate()
                .stream()
                .filter(l -> !l.getIsDeleted())
                .forEach( lesson ->
                lessonService.deleteLessonTemplateById(lesson.getId())
        );
        levelToDelete.setIsDeleted(true);
        levelTemplateRepository.save(levelToDelete);

    }

    public void deleteLevelByCourseId(Long id) {
            levelRepository.findByCourseId(id)
                    .stream()
                    .filter(l -> !l.getIsDeleted())
                    .forEach(l ->{
                        l.setIsDeleted(true);
                        lessonService.deleteLessonByLevelId(l.getId());
                        levelRepository.save(l);
                    });

    }


    public void checkIfLevelTemplateHaveLessonTemplate(Long levelTemplateId, Long lessonTemplateId){
        LessonTemplateEntity lessonTemplate  = lessonService.getLessonTemplateById(lessonTemplateId);
        LevelTemplateEntity levelTemplate = getLevelTemplateById(levelTemplateId);

        if(! levelTemplate.getLessonsTemplate().contains(lessonTemplate)){
            throw new IllegalStateException("Level not include this lesson");
        }
    }

    public void createNextLevelToStudent(CourseEntity courseEntity, Integer levelTemplateNumber) {
        LevelTemplateEntity levelToCreate =getLevelTemplateByNumber(levelTemplateNumber);
        levelRepository.save(new LevelEntity(courseEntity,levelToCreate));

    }

    public LevelTemplateEntity getLevelTemplateByNumber(int levelNumber) {
        return levelTemplateRepository. findOfTemplateByNumber(levelNumber)
                .orElseThrow(() -> new IllegalStateException("Error with creating new level"));
    }


    public int getCountOfLessonTemplateInLevelByNumber(int currentLevel) {
        LevelTemplateEntity levelTemplate = getLevelTemplateByNumber(currentLevel);

        return  levelTemplate.getLessonsTemplate().size();
    }
}
