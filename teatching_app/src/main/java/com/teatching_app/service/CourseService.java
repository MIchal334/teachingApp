package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.entity.CourseEntity;
import com.teatching_app.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;


    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseEntity getCourseById(Long id){
        return  courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotExistsException("Course with this id not exist"));
    }
}
