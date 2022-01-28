package com.teatching_app.validator;

import com.teatching_app.exceptionHandler.exception.ResourceAlreadyExistsException;
import com.teatching_app.model.dto.LessonTemplateDTO;
import com.teatching_app.repository.LessonTemplateRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonDataValidator {

    private final LessonTemplateRepository templateRepository;

    public LessonDataValidator(LessonTemplateRepository templateRepository) {

        this.templateRepository = templateRepository;
    }

    public void validData(LessonTemplateDTO newLesson, Long levelId) {
            greaterThan0(newLesson.getNumber());
            checkIfExist(newLesson.getNumber(),levelId);
            checkLengthTitle(newLesson.getTopic());
            checkIfTopicExist(newLesson.getTopic(),levelId);
    }

    private void checkIfTopicExist(String topic, Long levelId) {
        List<String> existingTopic = templateRepository.findTopicsOfLesson(levelId);

        if(existingTopic.contains(topic)){
            throw new ResourceAlreadyExistsException("Lesson with this topic exist");
        }
    }

    private void checkIfExist(Integer number, Long levelId) {
        List<Integer> existingNumber = templateRepository.findOfLessonNumbers(levelId);

        if(existingNumber.contains(number)){
            throw new ResourceAlreadyExistsException("Lesson with this number exist");
        }
    }

    private void checkLengthTitle(String topic) {

        if(topic.isEmpty()){
            throw new IllegalStateException("Title is empty");
        }

    }

    private void greaterThan0(int number){
        if(number < 1 ){
            throw  new IllegalStateException("number should  greater than 0");
        }
    }



}
