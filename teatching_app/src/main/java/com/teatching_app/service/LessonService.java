package com.teatching_app.service;

import com.teatching_app.model.dto.ExerciseDTO;
import com.teatching_app.model.dto.LessonContentDTO;
import com.teatching_app.model.entity.ExerciseEntity;
import com.teatching_app.model.entity.LessonContentEntity;
import com.teatching_app.model.entity.LessonEntity;
import com.teatching_app.repository.ExerciseRepository;
import com.teatching_app.repository.LessonContentRepository;
import com.teatching_app.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LessonService {
    private  final LessonRepository lessonRepository;
    private final LessonContentRepository lessonContentRepository;
    private final ExerciseRepository exerciseRepository;

    public LessonService(LessonRepository lessonRepository, LessonContentRepository lessonContentRepository, ExerciseRepository exerciseRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonContentRepository = lessonContentRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public LessonEntity saveNewLesson(LessonEntity lessonToSave){

        LessonEntity lesson = lessonRepository.save(lessonToSave);
        return lesson;
    }


    public void addContent(LessonEntity lesson, Set<LessonContentDTO> content) {
        content.forEach(c ->
        {
            lessonContentRepository.save(new LessonContentEntity(lesson,c));
        });
    }

    public void addExercise(LessonEntity lesson, Set<ExerciseDTO> exercise) {
        exercise.forEach(e ->{
            exerciseRepository.save(new ExerciseEntity(lesson,e));
        });
    }
}
