package com.teatching_app.validator;

import com.teatching_app.exceptionHandler.exception.ResourceAlreadyExistsException;
import com.teatching_app.model.dto.LevelDTO;
import com.teatching_app.repository.LevelTemplateRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelDataValidator {

    private final LevelTemplateRepository templateRepository;

    public LevelDataValidator(LevelTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public void validData(LevelDTO newLevel) {
            greaterThan0(newLevel.getNumber());
            checkIfExist(newLevel.getNumber());
            checkLengthTitle(newLevel.getTopic());
            checkIfTopicExist(newLevel.getTopic());
    }

    private void checkIfTopicExist(String topic) {
        List<String> existingTopic = templateRepository.findTopicsOfLevels();

        if(existingTopic.contains(topic)){
            throw new ResourceAlreadyExistsException("Level with this topic exist");
        }
    }

    private void checkIfExist(Integer number) {
        List<Integer> existingNumber = templateRepository.findOfLevelNumbers();

        if(existingNumber.contains(number)){
            throw new ResourceAlreadyExistsException("Level with this number exist");
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
