package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.entity.LevelEntity;
import com.teatching_app.repository.LevelRepository;
import org.springframework.stereotype.Service;

@Service
public class LevelService {
    private final LevelRepository levelRepository;

    public LevelService(LevelRepository courseRepository) {
        this.levelRepository = courseRepository;
    }

    public LevelEntity getLevelById(Long id){
        return  levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotExistsException("Level not exist"));
    }
}
