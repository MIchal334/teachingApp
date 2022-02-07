package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.dto.FinishLessonDTO;
import com.teatching_app.model.entity.*;
import com.teatching_app.repository.LevelRepository;
import com.teatching_app.repository.LevelTemplateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final LevelTemplateRepository levelTemplateRepository;
    private final LessonService lessonService;


    public LevelService(LevelRepository courseRepository, LevelTemplateRepository levelTemplateRepository, LessonService lessonService) {
        this.levelRepository = courseRepository;
        this.levelTemplateRepository = levelTemplateRepository;
        this.lessonService = lessonService;

    }

    public LevelTemplateEntity getLevelTemplateById(Long id) {
        return levelTemplateRepository.findById(id)
                .filter(l -> !l.getIsDeleted())
                .orElseThrow(() -> new ResourceNotExistsException("Level template not exist"));
    }

    public void deleteLevelTemplateById(Long levelId) {
        LevelTemplateEntity levelToDelete = getLevelTemplateById(levelId);
        levelToDelete.getLessonsTemplate()
                .stream()
                .filter(l -> !l.getIsDeleted())
                .forEach(lesson ->
                        lessonService.deleteLessonTemplateById(lesson.getId())
                );
        levelToDelete.setIsDeleted(true);
        levelTemplateRepository.save(levelToDelete);

    }

    public void deleteLevelByCourseId(Long id) {
        levelRepository.findLevelsByCourseId(id)
                .stream()
                .filter(l -> !l.getIsDeleted())
                .forEach(l -> {
                    l.setIsDeleted(true);
                    lessonService.deleteLessonByLevelId(l.getId());
                    levelRepository.save(l);
                });

    }


    public void checkIfLevelTemplateHaveLessonTemplate(Long levelTemplateId, Long lessonTemplateId) {
        LessonTemplateEntity lessonTemplate = lessonService.getLessonTemplateById(lessonTemplateId);
        LevelTemplateEntity levelTemplate = getLevelTemplateById(levelTemplateId);

        if (!levelTemplate.getLessonsTemplate().contains(lessonTemplate)) {
            throw new IllegalStateException("Level not include this lesson");
        }
    }

    public void createNextLevelToStudent(CourseEntity courseEntity, Integer levelTemplateNumber) {
        LevelTemplateEntity levelToCreate = getLevelTemplateByNumber(levelTemplateNumber);
        levelRepository.save(new LevelEntity(courseEntity, levelToCreate));

    }

    public LevelTemplateEntity getLevelTemplateByNumber(int levelNumber) {
        return levelTemplateRepository.findOfTemplateByNumber(levelNumber)
                .orElseThrow(() -> new IllegalStateException("Error with creating new level"));
    }


    public int getCountOfLessonTemplateInLevelByNumber(int currentLevel) {
        LevelTemplateEntity levelTemplate = getLevelTemplateByNumber(currentLevel);

        return levelTemplate.getLessonsTemplate().size();
    }

    public void updateAvgScore(FinishLessonDTO data, UserEntity student,Float score) {
        LevelEntity levelToUpdate = levelRepository
                .findById(data.getCurrentLevelId())
                .orElseThrow(() -> new ResourceNotExistsException("Level not exist"));

        long countOfFinished =
                levelToUpdate.getLessons()
                        .stream()
                        .filter(o -> o.getIsFinished())
                        .count();

        int countOfAllLesson = levelToUpdate.getLevelTemplate().getLessonsTemplate().size();

        Float currentAvg = levelToUpdate.getAverageScore()*(countOfFinished-1);
        Float newAvg = (currentAvg + score) / countOfFinished;
        Float levelOfCompletion = Float.valueOf( ((float)countOfFinished)/countOfAllLesson) *100;

        levelToUpdate.setAverageScore(newAvg);
        levelToUpdate.setLevelOfCompletion(levelOfCompletion);
        levelRepository.save(levelToUpdate);

    }


    public void finishLevelById(Long currentLevelId) {
        LevelEntity levelToUpdate = levelRepository.findById(currentLevelId)
                .orElseThrow(()-> new ResourceNotExistsException("Level not exist"));
        levelToUpdate.setDateOfEnd(LocalDate.now());
        levelToUpdate.setIsFinished(true);

        levelRepository.save(levelToUpdate);
    }
}
