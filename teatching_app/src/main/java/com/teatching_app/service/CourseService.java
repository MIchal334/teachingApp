package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.entity.CourseEntity;
import com.teatching_app.model.entity.UserEntity;
import com.teatching_app.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LevelService levelService;


    public CourseService(CourseRepository courseRepository, LevelService levelService) {
        this.courseRepository = courseRepository;
        this.levelService = levelService;
    }

    public CourseEntity getCourseById(Long id){
        return  courseRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new ResourceNotExistsException("Course with this id not exist"));
    }

    public void deleteCourseOfUser(UserEntity userToDelete) {
                 courseRepository
                    .findAllByUserId(userToDelete.getId())
                    .stream()
                    .filter(c -> !c.getIsDeleted())
                    .forEach(c -> {
                        c.setIsDeleted(true);
                        levelService.deleteLevelByCourseId(c.getId());
                        courseRepository.save(c);
                    });


    }
}
