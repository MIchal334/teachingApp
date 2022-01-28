package com.teatching_app.service;

import com.teatching_app.model.dto.LessonTemplateDTO;
import com.teatching_app.model.dto.LevelTemplateDTO;
import com.teatching_app.model.dto.UserDTO;
import com.teatching_app.model.entity.ExerciseEntity;
import com.teatching_app.model.entity.LessonContentEntity;
import com.teatching_app.model.entity.LessonTemplateEntity;
import com.teatching_app.model.entity.LevelTemplateEntity;
import com.teatching_app.repository.LessonTemplateRepository;
import com.teatching_app.repository.LevelTemplateRepository;
import com.teatching_app.validator.LessonDataValidator;
import com.teatching_app.validator.LevelDataValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final LevelService levelService;
    private final LessonService lessonService;
    private final LessonDataValidator lessonDataValidator;
    private final LevelDataValidator levelDataValidator;
    private final LevelTemplateRepository levelTemplateRepository;
    private final UserService userService;
    private final LessonTemplateRepository lessonTemplateRepository;


    public AdminService(LevelService levelService, LessonService lessonService, LessonDataValidator lessonDataValidator, LevelDataValidator levelDataValidator, LevelTemplateRepository levelTemplateRepository, UserService userService, LessonTemplateRepository lessonTemplateRepository) {

        this.levelService = levelService;
        this.lessonService = lessonService;
        this.lessonDataValidator = lessonDataValidator;
        this.levelDataValidator = levelDataValidator;
        this.levelTemplateRepository = levelTemplateRepository;
        this.userService = userService;
        this.lessonTemplateRepository = lessonTemplateRepository;
    }

    public LevelTemplateEntity addNewLevel(LevelTemplateDTO newLevel) {
        levelDataValidator.validData(newLevel);

        LevelTemplateEntity newLevelTemplate = new LevelTemplateEntity(newLevel);

        return levelTemplateRepository.save(newLevelTemplate);
    }


    public LessonTemplateDTO addNewLesson(Long levelId, LessonTemplateDTO newLesson) {
        lessonDataValidator.validData(newLesson, levelId);

        LevelTemplateEntity levelTemplate = levelService.getLevelTemplateById(levelId);
        LessonTemplateEntity lessonTemplate = lessonService.saveNewLessonTemplate(newLesson,levelTemplate);

        Set<LessonContentEntity> contetnt = lessonService.addContent(lessonTemplate,newLesson.getContent());
        Set<ExerciseEntity> exercise = lessonService.addExercise(lessonTemplate,newLesson.getExercise());

        lessonTemplate.setLessonContents(contetnt);
        lessonTemplate.setLessonExercises(exercise);
        return new LessonTemplateDTO(lessonTemplate);
    }

    public List<UserDTO> getAllUser() {
        List<UserDTO> listOfAllUser =  userService.getAllUser();
        return listOfAllUser;
    }

    public void deleteUserByAdmin(Long id) {
        userService.deleteUser(id);
    }

    public List<LevelTemplateDTO> findAllLevelTemplate() {
        return levelTemplateRepository.findAll()
                .stream()
                .filter(l -> !l.getIsDeleted() )
                .map(LevelTemplateDTO::new)
                .collect(Collectors.toList());


    }

    public List<LessonTemplateDTO> lessonTemplateInLevel(Long levelId) {
        LevelTemplateEntity level = levelService.getLevelTemplateById(levelId);

        return lessonTemplateRepository.findAllLessonOfLevel(level)
                .stream()
                .filter(l -> !l.getIsDeleted())
                .map(LessonTemplateDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteLevelTemplateById(Long  levelId) {
        levelService.deleteLevelTemplateById(levelId);

    }

    public void deleteLessonTemplateById(Long levelId, Long lessonId) {
        levelService.checkIfLevelTemplateHaveLessonTemplate(levelId,lessonId);
        lessonService.deleteLessonTemplateById(lessonId);
    }
}
