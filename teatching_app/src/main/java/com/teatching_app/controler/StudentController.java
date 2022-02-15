package com.teatching_app.controler;

import com.teatching_app.model.dto.FinishLessonDTO;
import com.teatching_app.service.StudentService;
import com.teatching_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final UserService userService;
    private final StudentService studentService;

    public StudentController(UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    @GetMapping
    ResponseEntity<?> getStudentProfile() {
        var currentUser = userService.getCurrentUser();
        var result = studentService.getAllDataAboutStudentCourse(currentUser.getId());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    ResponseEntity<?> startNewCourseByStudent() {
        var currentUser = userService.getCurrentUser();
        var result = studentService.startNewCourseByStudent(currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/lesson")
    ResponseEntity<?> startNextLesson(){
        var currentStudent = userService.getCurrentUser();
        var result = studentService.startNextLessonByStudent(currentStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/lesson")
    ResponseEntity<?> finishLesson(@RequestBody FinishLessonDTO dataAboutFinishedLesson){
        var currentStudent = userService.getCurrentUser();
        var result = studentService.finishLesson(dataAboutFinishedLesson,currentStudent);
        return ResponseEntity.ok(result);
    }

}
