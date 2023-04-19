package pl.polsl.ior.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiStudent;
import pl.polsl.ior.spring.api.conversion.StudentApiConversion;
import pl.polsl.ior.spring.service.StudentService;

import java.time.OffsetDateTime;
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

    @GetMapping(params = {"firstName", "surname"})
    public ResponseEntity<List<ApiStudent>> findByFNameAndSName(@RequestParam String firstName, @RequestParam String surname) {
        return ResponseEntity.ok(studentService.findByFNameAndSName(firstName, surname)
                .stream()
                .map(StudentApiConversion::toApi)
                .toList());
    }

    @GetMapping(params = {"firstName", "surname", "pageNumber", "pageSize"})
    public ResponseEntity<List<ApiStudent>> findByFNameAndSName(@RequestParam String firstName,
                                                                @RequestParam String surname,
                                                                @RequestParam Integer pageNumber,
                                                                @RequestParam Integer pageSize) {
        return ResponseEntity.ok(studentService.findByFNameAndSName(firstName, surname, pageSize, pageNumber)
                .stream()
                .map(StudentApiConversion::toApi)
                .toList());
    }

    @GetMapping(params = {"firstNameLike"})
    public ResponseEntity<List<ApiStudent>> findByFNameLike(@RequestParam String firstNameLike) {
        return ResponseEntity.ok(studentService.findByFNameLike(firstNameLike)
                .stream()
                .map(StudentApiConversion::toApi)
                .toList());
    }

    @GetMapping(params = {"distinctFirstName"})
    public ResponseEntity<List<ApiStudent>> findDistinctByFName(@RequestParam String distinctFirstName) {
        return ResponseEntity.ok(studentService.findDistinctByFName(distinctFirstName)
                .stream()
                .map(StudentApiConversion::toApi)
                .toList());
    }

    @GetMapping(params = {"orFirstName", "orSurname"})
    public ResponseEntity<List<ApiStudent>> findByFNameOrSName(@RequestParam String orFirstName, @RequestParam String orSurname) {
        return ResponseEntity.ok(studentService.findByFNameOrSName(orFirstName, orSurname)
                .stream()
                .map(StudentApiConversion::toApi)
                .toList());
    }

    @GetMapping(params = {"medicalTestsContain"})
    public ResponseEntity<List<ApiStudent>> findByMedicalTestsContaining(@RequestParam String medicalTestsContain) {
        return ResponseEntity.ok(studentService.findByMedicalTestsContaining(medicalTestsContain)
                .stream()
                .map(StudentApiConversion::toApi)
                .toList());
    }

    @GetMapping(params = {"from", "to"})
    public ResponseEntity<List<ApiStudent>> findWithFlightsBetween(@RequestParam OffsetDateTime from, @RequestParam OffsetDateTime to) {
        return ResponseEntity.ok(studentService.findWithFlightsBetween(from, to)
                .stream()
                .map(StudentApiConversion::toApi)
                .toList());
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<List<ApiStudent>> deleteById(@RequestParam Long id) {
        return ResponseEntity.ok(studentService.deleteById(id)
                .stream()
                .map(StudentApiConversion::toApi)
                .toList());
    }
}

