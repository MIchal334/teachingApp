package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
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
                .orElseThrow(() -> new ResourceNotExistsException("Level template not exist"));
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
}
