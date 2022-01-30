package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.dto.ExerciseDTO;
import com.teatching_app.model.dto.LessonContentDTO;
import com.teatching_app.model.dto.LessonTemplateDTO;
import com.teatching_app.model.entity.*;
import com.teatching_app.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonTemplateRepository lessonTemplateRepository;
    private final LessonContentRepository lessonContentRepository;
    private final ExerciseRepository exerciseRepository;
    private final AnswerRepository answerRepository;

    public LessonService(LessonRepository lessonRepository, LessonTemplateRepository lessonTemplateRepository, LessonContentRepository lessonContentRepository, ExerciseRepository exerciseRepository, AnswerRepository answerRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.lessonContentRepository = lessonContentRepository;
        this.exerciseRepository = exerciseRepository;
        this.answerRepository = answerRepository;
    }

    public LessonTemplateEntity saveNewLessonTemplate(LessonTemplateDTO newLesson, LevelTemplateEntity levelTemplate) {
        LessonTemplateEntity lessonToSave = new LessonTemplateEntity(newLesson, levelTemplate);
        return lessonTemplateRepository.save(lessonToSave);
    }


    public Set<LessonContentEntity> addContent(LessonTemplateEntity lesson, Set<LessonContentDTO> content) {
        Set<LessonContentEntity> addedContent = new HashSet<>();
        content.forEach(c ->
        {
            addedContent.add(lessonContentRepository.save(new LessonContentEntity(lesson, c)));
        });
        return addedContent;
    }

    public Set<ExerciseEntity> addExercise(LessonTemplateEntity lesson, Set<ExerciseDTO> exercise) {
        Set<ExerciseEntity> addedExercise = new HashSet<>();
        exercise.forEach(e -> {
            Set<AnswerEntity> addedAnswer = new HashSet<>();
            ExerciseEntity newExercise = exerciseRepository.save(new ExerciseEntity(lesson, e));

            e.getAnswers().forEach(a -> {
                addedAnswer.add(answerRepository.save(new AnswerEntity(a, newExercise)));
            });
            newExercise.setAnswers(addedAnswer);
            addedExercise.add(newExercise);
        });
        return addedExercise;
    }

    public void deleteLessonByLevelId(Long id) {
        lessonRepository.findAllByLevelId(id)
                .stream()
                .filter(l -> !l.getIsDeleted())
                .forEach(l -> {
                    l.setIsDeleted(true);
                    lessonRepository.save(l);
                });
    }

    public void deleteLessonTemplateById(Long id) {
        LessonTemplateEntity lessonToDelete = getLessonTemplateById(id);
        deleteLessonContent(lessonToDelete);
        deleteLessonExercise(lessonToDelete);
        lessonToDelete.setIsDeleted(true);
        lessonTemplateRepository.save(lessonToDelete);

    }


    public LessonTemplateEntity getLessonTemplateById(Long id) {
        return lessonTemplateRepository.findById(id)
                .filter(l -> !l.getIsDeleted())
                .orElseThrow(() -> new ResourceNotExistsException("Lesson template not exist"));
    }

    private void deleteLessonContent(LessonTemplateEntity lessonToDelete) {
        lessonToDelete
                .getLessonContents()
                .forEach(content -> {
                    content.setIsDeleted(true);
                    lessonContentRepository.save(content);
                });
    }

    private void deleteLessonExercise(LessonTemplateEntity lessonToDelete) {
        lessonToDelete
                .getLessonExercises()
                .forEach(exr -> {
                    exr.getAnswers()
                            .forEach(answer -> {
                                answer.setIsDeleted(true);
                                answerRepository.save(answer);
                            });
                    exr.setIsDeleted(true);
                    exerciseRepository.save(exr);
                });
    }


}
