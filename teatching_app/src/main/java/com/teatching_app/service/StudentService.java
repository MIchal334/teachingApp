package com.teatching_app.service;

import com.teatching_app.model.dto.CourseDTO;
import com.teatching_app.model.entity.CourseEntity;
import com.teatching_app.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final CourseService courseService;

    public StudentService(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<CourseEntity> getAllDataAboutStudentCourse(Long studentId) {
        List<CourseEntity> courseEntity =  courseService.getCourseByStudentId(studentId);

        return courseEntity;
    }

    public CourseDTO startNewCourseByStudent(UserEntity student) {
        CourseEntity courseEntity = courseService.startNewStudentCourse(student);
        return new CourseDTO(courseEntity);
    }
}