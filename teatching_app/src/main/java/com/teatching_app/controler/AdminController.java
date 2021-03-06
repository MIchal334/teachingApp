package com.teatching_app.controler;

import com.teatching_app.model.dto.LessonTemplateDTO;
import com.teatching_app.model.dto.LevelTemplateDTO;
import com.teatching_app.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")

public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user")
    ResponseEntity<?> showAllUser() {
        var result = adminService.getAllUser();
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/user/{userId}")
    ResponseEntity<?> deleteUserByAdmin(@PathVariable Long id) {
        adminService.deleteUserByAdmin(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/level")
    ResponseEntity<?> showAllLevelTemplate() {
        var result = adminService.findAllLevelTemplate();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/level/{levelId}")
    ResponseEntity<?> showAllLessonTemplateInLevel(@PathVariable("levelId") Long levelId) {
        var result = adminService.lessonTemplateInLevel(levelId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/level/{levelId}")
    ResponseEntity<?> addNewLessonTemplateToLevel(@PathVariable(name = "levelId") Long levelId,
                                                  @RequestBody LessonTemplateDTO newLesson) {
        var result = adminService.addNewLesson(levelId, newLesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/level")
    ResponseEntity<?> addNewLevelTemplate(@RequestBody LevelTemplateDTO newLevel) {
        var result = adminService.addNewLevel(newLevel);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @PatchMapping("/level/{levelId}")
    ResponseEntity<?> deleteLevelTemplateByAdmin(@PathVariable("levelId") Long levelId) {
        adminService.deleteLevelTemplateById(levelId);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/level/{levelId}/{lessonId}")
    ResponseEntity<?> deleteLessonTemplateInLevelByAdmin(@PathVariable("levelId") Long levelId,
                                                         @PathVariable("lessonId") Long lessonId) {
        adminService.deleteLessonTemplateById(levelId,lessonId);
        return ResponseEntity.ok().build();
    }


}
