package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceAlreadyExistsException;
import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.dto.FinishLessonDTO;
import com.teatching_app.model.entity.CourseEntity;
import com.teatching_app.model.entity.LevelEntity;
import com.teatching_app.model.entity.UserEntity;
import com.teatching_app.repository.CourseRepository;
import com.teatching_app.repository.LevelTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LevelService levelService;
    private final LevelTemplateRepository levelTemplateRepository;


    public CourseService(CourseRepository courseRepository, LevelService levelService, LevelTemplateRepository levelTemplateRepository) {
        this.courseRepository = courseRepository;
        this.levelService = levelService;
        this.levelTemplateRepository = levelTemplateRepository;
    }

    public CourseEntity getCourseById(Long id) {
        return courseRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new ResourceNotExistsException("Course with this id not exist"));
    }

    public void deleteCourseOfUser(UserEntity userToDelete) {
        courseRepository
                .findAllByUserIdAndSubject(userToDelete.getId(), "Angielski")
                .stream()
                .filter(c -> !c.getIsDeleted())
                .forEach(c -> {
                    c.setIsDeleted(true);
                    levelService.deleteLevelByCourseId(c.getId());
                    courseRepository.save(c);
                });


    }

    public CourseEntity getCourseByStudentIdAndSubject(Long studentId, String subject) {
        Optional<CourseEntity> courseOfStudent = courseRepository.findAllByUserIdAndSubject(studentId, subject);

        return courseOfStudent.orElseThrow(() -> new ResourceNotExistsException("Student have not course"));
    }

    public CourseEntity startNewStudentCourse(UserEntity student) {

        if (checkIfStudentHaveCourse(student.getId(), "Angielski")) {
            throw new ResourceAlreadyExistsException("Student with this id have start course in system");
        }

        return courseRepository.save(new CourseEntity(student));
    }

    private boolean checkIfStudentHaveCourse(Long studentId, String subject) {
        int size = courseRepository.findAllByUserIdAndSubject(studentId, subject)
                .stream()
                .filter(o -> o.getSubject().equals(subject))
                .collect(Collectors.toList()).size();

        if (size != 0) {
            return true;
        }

        return false;
    }

    public int getCountOfLevelTemplateInCourse() {
        return levelTemplateRepository.findAll().size();
    }

    public void updateAvgScore(FinishLessonDTO dataAboutFinishedLesson, UserEntity currentStudent, Float newLessonResult) {
        CourseEntity course = this.getCourseByStudentIdAndSubject(currentStudent.getId(), "Angielski");
        Long countOfFinished = 0l;
        Set<LevelEntity> levels = course.getLevels();

        for(LevelEntity l : levels){
            countOfFinished += l.getLessons().stream().count();
        }

        Float currentAvg = course.getAverageScore();
        float newAvg = (currentAvg + newLessonResult) / countOfFinished;

        course.setAverageScore(newAvg);
        courseRepository.save(course);
    }
}
