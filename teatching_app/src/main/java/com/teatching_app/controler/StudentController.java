package com.teatching_app.controler;

import com.teatching_app.service.StudentService;
import com.teatching_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
