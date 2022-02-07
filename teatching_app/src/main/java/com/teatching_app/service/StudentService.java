package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.dto.*;
import com.teatching_app.model.entity.*;
import com.teatching_app.repository.CourseRepository;
import com.teatching_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final CourseService courseService;
    private final LevelService levelService;
    private final LessonService lessonService;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;


    public StudentService(CourseService courseService, LevelService levelService, LessonService lessonService, UserRepository userRepository, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.levelService = levelService;
        this.lessonService = lessonService;

        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public StatDTO getAllDataAboutStudentCourse(Long studentId) {
        CourseEntity courseEntity = courseService.getCourseByStudentIdAndSubject(studentId, "Angielski");
        List<LevelStatDTO> listOfLevelStat = new ArrayList<>();

        courseEntity
                .getLevels()
                .forEach(level -> {
                    List<LessonStatDTO> lessonStat =
                            level.getLessons()
                                    .stream()
                                    .filter(LessonEntity::getIsFinished)
                                    .map(LessonStatDTO::new)
                                    .collect(Collectors.toList());
                    LevelStatDTO levelStatDTO = new LevelStatDTO(level,lessonStat);
                    listOfLevelStat.add(levelStatDTO);
                });

        StatDTO statDTO = new StatDTO(courseEntity,listOfLevelStat);

        return statDTO;
    }

    public CourseDTO startNewCourseByStudent(UserEntity student) {
        CourseEntity courseEntity = courseService.startNewStudentCourse(student);

        levelService.createNextLevelToStudent(courseEntity, student.getCurrentLevel() + 1);

        student.setCurrentLevel(student.getCurrentLevel() + 1);
        userRepository.save(student);

        return new CourseDTO(courseEntity);
    }

    public StartLessonDTO startNextLessonByStudent(UserEntity currentStudent) {

        CourseEntity course = courseService.getCourseByStudentIdAndSubject(currentStudent.getId(), "Angielski");

        if (course.getIsFinished()) {
            throw new IllegalStateException("Course is finished");
        }

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


    public String finishLesson(FinishLessonDTO dataAboutFinishedLesson, UserEntity currentStudent) {

        lessonService.finishLessonById(dataAboutFinishedLesson);
        levelService.updateAvgScore(dataAboutFinishedLesson, currentStudent, dataAboutFinishedLesson.getScore());
        courseService.updateAvgScore(dataAboutFinishedLesson, currentStudent, dataAboutFinishedLesson.getScore());

        boolean isFinish = assignNewLessonToStudent(currentStudent, dataAboutFinishedLesson);

        if (isFinish) {
            return "Koneic";
        }

        return "OK";

    }

    private boolean assignNewLessonToStudent(UserEntity currentStudent, FinishLessonDTO dataAboutFinishedLesson) {
        boolean finish = false;
        int currentLevel = currentStudent.getCurrentLevel();
        int countOfLessonInLevel = levelService.getCountOfLessonTemplateInLevelByNumber(currentLevel);
        int countOfLevelInCourse = courseService.getCountOfLevelTemplateInCourse();


        if (countOfLessonInLevel == dataAboutFinishedLesson.getLessonTemplateNumber()) {
            CourseEntity course = courseService.getCourseByStudentIdAndSubject(currentStudent.getId(), "Angielski");
            levelService.finishLevelById(dataAboutFinishedLesson.getCurrentLevelId());

            if (currentLevel < countOfLevelInCourse || course.getIsFinished()) {
                currentStudent.setCurrentLevel(currentLevel + 1);
                levelService.createNextLevelToStudent(course, currentLevel + 1);
                currentStudent.setLastLesson(0);
            } else {
                course.setIsFinished(true);
                course.setDateOfEnd(LocalDate.now());
                courseRepository.save(course);
                return true;
            }

        } else {
            currentStudent.setLastLesson(dataAboutFinishedLesson.getLessonTemplateNumber());
        }
        userRepository.save(currentStudent);
        return finish;
    }
}
