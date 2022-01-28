package com.teatching_app.service;

import com.teatching_app.model.dto.ExerciseDTO;
import com.teatching_app.model.dto.LessonContentDTO;
import com.teatching_app.model.dto.LessonDTO;
import com.teatching_app.model.entity.*;
import com.teatching_app.repository.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LessonService {
    private  final LessonRepository lessonRepository;
    private final LessonTemplateRepository lessonTemplateRepository;
    private final LessonContentRepository lessonContentRepository;
    private final ExerciseRepository exerciseRepository;
    private  final AnswerRepository answerRepository;

    public LessonService(LessonRepository lessonRepository, LessonTemplateRepository lessonTemplateRepository, LessonContentRepository lessonContentRepository, ExerciseRepository exerciseRepository, AnswerRepository answerRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.lessonContentRepository = lessonContentRepository;
        this.exerciseRepository = exerciseRepository;
        this.answerRepository = answerRepository;
    }

    public LessonTemplateEntity saveNewLessonTemplate(LessonDTO newLesson, LevelTemplateEntity levelTemplate) {
            LessonTemplateEntity lessonToSave = new LessonTemplateEntity(newLesson,levelTemplate);
            return lessonTemplateRepository.save(lessonToSave);
    }


    public void addContent(LessonTemplateEntity lesson, Set<LessonContentDTO> content) {
        content.forEach(c ->
        {
            lessonContentRepository.save(new LessonContentEntity(lesson,c));
        });
    }

    public void addExercise(LessonTemplateEntity lesson, Set<ExerciseDTO> exercise) {
        exercise.forEach(e ->{

            ExerciseEntity newExercise = exerciseRepository.save(new ExerciseEntity(lesson,e));

            e.getAnswers().forEach(a->{
                answerRepository.save(new AnswerEntity(a,newExercise));
            });

        });
    }

    public void deleteLessonByLevelId(Long id) {
        lessonRepository.findAllByLevelId(id)
                .stream()
                .filter(l -> !l.getIsDeleted())
                .forEach(l ->{
                    l.setIsDeleted(true);
                });
    }
}
