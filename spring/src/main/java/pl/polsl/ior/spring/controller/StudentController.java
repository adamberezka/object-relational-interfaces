package pl.polsl.ior.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiStudent;
import pl.polsl.ior.spring.api.conversion.StudentApiConversion;
import pl.polsl.ior.spring.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.Student.ENDPOINT)
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<ApiStudent>> getAll() {
        final List<ApiStudent> apiStudents = studentService.getAll()
                .stream()
                .map(StudentApiConversion::toApi)
                .toList();
        return ResponseEntity.ok(apiStudents);
    }
}

