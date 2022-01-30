package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceAlreadyExistsException;
import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.entity.CourseEntity;
import com.teatching_app.model.entity.UserEntity;
import com.teatching_app.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CourseEntity> getCourseByStudentId(Long studentId) {
        List<CourseEntity> coursesOfStudent = courseRepository.findAllByUserId(studentId);

        if(coursesOfStudent.size() < 1){
            throw new IllegalStateException("Student have not any courses");
        }

        return courseRepository.findAllByUserId(studentId);
    }

    public CourseEntity startNewStudentCourse(UserEntity student) {

        if(checkIfStudentHaveCourse(student.getId(),"Angielski")){
            throw new ResourceAlreadyExistsException("Student with this id have start course in system");
        }

        return courseRepository.save(new CourseEntity(student));
    }

    private boolean checkIfStudentHaveCourse(Long studentId, String  subject){
        int size = courseRepository.findAllByUserId(studentId)
                .stream()
                .filter(o -> o.getSubject().equals(subject))
                .collect(Collectors.toList()).size();

        if(size != 0){
            return true;
        }

        return false;
    }
}
