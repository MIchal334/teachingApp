package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.dto.*;
import com.teatching_app.model.entity.*;
import com.teatching_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final CourseService courseService;
    private final LevelService levelService;
    private final LessonService lessonService;
    private final UserRepository userRepository;


    public StudentService(CourseService courseService, LevelService levelService, LessonService lessonService, UserRepository userRepository) {
        this.courseService = courseService;
        this.levelService = levelService;
        this.lessonService = lessonService;

        this.userRepository = userRepository;
    }

    public List<CourseEntity> getAllDataAboutStudentCourse(Long studentId) {
        CourseEntity courseEntity = courseService.getCourseByStudentIdAndSubject(studentId,"Angielski");
        return null;
    }

    public CourseDTO startNewCourseByStudent(UserEntity student) {
        CourseEntity courseEntity = courseService.startNewStudentCourse(student);

        levelService.createNextLevelToStudent(courseEntity, student.getCurrentLevel() + 1);

        student.setCurrentLevel(student.getCurrentLevel() + 1);
        userRepository.save(student);

        return new CourseDTO(courseEntity);
    }

    public StartLessonDTO startNextLessonByStudent(UserEntity currentStudent) {
        int currentLevelNumber = currentStudent.getCurrentLevel();
        int lastLessonNumber = currentStudent.getLastLesson();
        LevelTemplateEntity currentLevelTemplate = levelService.getLevelTemplateByNumber(currentLevelNumber);

        LessonTemplateEntity currentLessonTemplate = currentLevelTemplate.getLessonsTemplate()
                .stream()
                .filter(l -> l.getNumber() == (lastLessonNumber + 1))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Error with start lesson"));


        LevelEntity levelEntity = currentStudent.getUserCourses()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotExistsException("Studnet have not course"))
                .getLevels()
                .stream()
                .filter(l -> l.getLevelTemplate().equals(currentLevelTemplate))
                .findFirst()
                .orElseThrow(() -> new ResourceNotExistsException("Error with start lesson"));

        LessonEntity lessonEntity = lessonService.startNextLesson(levelEntity, currentLessonTemplate);
        return new StartLessonDTO(new LevelTemplateDTO(currentLevelTemplate), new LessonTemplateDTO(currentLessonTemplate),
                levelEntity.getId(), lessonEntity.getId(), lastLessonNumber + 1);
    }


    public void finishLesson(FinishLessonDTO dataAboutFinishedLesson, UserEntity currentStudent) {
        lessonService.finishLessonById(dataAboutFinishedLesson);
        assignNewLessonToStudent(currentStudent, dataAboutFinishedLesson);
        //TODO Zaktualizować wszelkie wyniki
    }

    private void assignNewLessonToStudent(UserEntity currentStudent, FinishLessonDTO dataAboutFinishedLesson) {
        // TODO ZABAC o odpowiwednia ifrormacje przy ukonczeniu kuru
        int currentLevel = currentStudent.getCurrentLevel();
        int countOfLessonInLevel = levelService.getCountOfLessonTemplateInLevelByNumber(currentLevel);
        int countOfLevelInCourse = courseService.getCountOfLevelTemplateInCourse();


        if (countOfLessonInLevel == dataAboutFinishedLesson.getLessonTemplateNumber()) {
            if (currentLevel < countOfLevelInCourse) {
                CourseEntity course = courseService.getCourseByStudentIdAndSubject(currentStudent.getId(),"Angielski");
                currentStudent.setCurrentLevel(currentLevel + 1);
                levelService.createNextLevelToStudent(course, currentLevel + 1);
                currentStudent.setLastLesson(0);
            }

        } else {
            currentStudent.setLastLesson(dataAboutFinishedLesson.getLessonTemplateNumber());
        }
        userRepository.save(currentStudent);
    }
}
