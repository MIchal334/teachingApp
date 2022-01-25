package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.entity.LevelEntity;
import com.teatching_app.model.entity.LevelTemplateEntity;
import com.teatching_app.repository.LevelRepository;
import com.teatching_app.repository.LevelTemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final LevelTemplateRepository levelTemplateRepository;

    public LevelService(LevelRepository courseRepository, LevelTemplateRepository levelTemplateRepository) {
        this.levelRepository = courseRepository;
        this.levelTemplateRepository = levelTemplateRepository;
    }

    public LevelTemplateEntity getLevelTemplateById(Long id){
        return  levelTemplateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotExistsException("Level template not exist"));
    }
}
