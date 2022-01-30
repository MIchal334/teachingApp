package com.teatching_app.service;

import com.teatching_app.model.dto.CourseDTO;
import com.teatching_app.model.entity.CourseEntity;
import com.teatching_app.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final CourseService courseService;
    private final LevelService levelService;
    private final LessonService lessonService;

    public StudentService(CourseService courseService, LevelService levelService, LessonService lessonService) {
        this.courseService = courseService;
        this.levelService = levelService;
        this.lessonService = lessonService;
    }

    public List<CourseEntity> getAllDataAboutStudentCourse(Long studentId) {
        List<CourseEntity> courseEntity =  courseService.getCourseByStudentId(studentId);

        return courseEntity;
    }

    public CourseDTO startNewCourseByStudent(UserEntity student) {
        CourseEntity courseEntity = courseService.startNewStudentCourse(student);
        levelService.createFirstLevel(courseEntity);
        return new CourseDTO(courseEntity);
    }
}
