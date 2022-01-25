package com.teatching_app.controler;

import com.teatching_app.model.dto.LessonDTO;
import com.teatching_app.model.dto.LevelDTO;
import com.teatching_app.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController{

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/{levelId}")
    ResponseEntity<?> addLessonToLevel(@PathVariable(name ="levelId") Long levelId,
                                       @RequestBody LessonDTO newLesson){

        var result = adminService.addNewLesson(levelId,newLesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping
    ResponseEntity<?> addNewLevel(@RequestBody LevelDTO newLevel){
        var result = adminService.addNewLevel(newLevel);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
