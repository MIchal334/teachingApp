package com.teatching_app.controler;

import com.teatching_app.model.dto.LessonDTO;
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

    @PostMapping("/course/{courseId}/{levelId}")
    ResponseEntity<?> addLessonToLevel(@PathVariable(name = "courseId") Long courseId,
                                       @PathVariable(name ="levelId") Long levelId,
                                       @RequestBody LessonDTO newLesson){

        var result = adminService.addNewLesson(courseId,levelId,newLesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
