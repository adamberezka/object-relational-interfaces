package pl.polsl.ior.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiCourse;
import pl.polsl.ior.spring.api.conversion.CourseApiConversion;
import pl.polsl.ior.spring.api.request.course.CreateCourseRequest;
import pl.polsl.ior.spring.api.response.course.CreateCourseResponse;
import pl.polsl.ior.spring.domain.Course;
import pl.polsl.ior.spring.service.CourseService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.Course.ENDPOINT)
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<ApiCourse>> getAll() {
        return ResponseEntity.ok(
                courseService.getAll()
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = {"certType", "startDateFrom", "startDateTo"})
    public ResponseEntity<List<ApiCourse>> findByCertTypeAndStartDateBetween(@RequestParam String certType,
                                                                             @RequestParam OffsetDateTime startDateFrom,
                                                                             @RequestParam OffsetDateTime startDateTo) {
        return ResponseEntity.ok(
                courseService.findByCertTypeAndStartDateBetween(certType, startDateFrom, startDateTo)
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = {"certType", "startDateFrom", "startDateTo", "pageSize", "pageNumber"})
    public ResponseEntity<List<ApiCourse>> findByCertTypeAndStartDateBetween(@RequestParam String certType,
                                                                             @RequestParam OffsetDateTime startDateFrom,
                                                                             @RequestParam OffsetDateTime startDateTo,
                                                                             @RequestParam Integer pageSize,
                                                                             @RequestParam Integer pageNumber) {
        return ResponseEntity.ok(
                courseService.findByCertTypeAndStartDateBetween(certType, startDateFrom, startDateTo, pageSize, pageNumber)
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = "certTypeLike")
    public ResponseEntity<List<ApiCourse>> findByCertTypeLike(@RequestParam String certTypeLike) {
        return ResponseEntity.ok(
                courseService.findByCertTypeLike(certTypeLike)
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = "distinctByCertType")
    public ResponseEntity<List<ApiCourse>> findDistinctByCertType(@RequestParam String distinctByCertType) {
        return ResponseEntity.ok(
                courseService.findDistinctByCertType(distinctByCertType)
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = {"orCertType", "orStartDateBetweenFrom", "orStartDateBetweenTo"})
    public ResponseEntity<List<ApiCourse>> findByCertTypeOrStartDateBetween(@RequestParam String orCertType,
                                                                            @RequestParam OffsetDateTime orStartDateBetweenFrom,
                                                                            @RequestParam OffsetDateTime orStartDateBetweenTo) {
        return ResponseEntity.ok(
                courseService.findByCertTypeOrStartDateBetween(orCertType, orStartDateBetweenFrom, orStartDateBetweenTo)
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = "descriptionContains")
    public ResponseEntity<List<ApiCourse>> findByDescriptionContainingIgnoreCase(@RequestParam String descriptionContains) {
        return ResponseEntity.ok(
                courseService.findByDescriptionContainingIgnoreCase(descriptionContains)
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = "withMoreThanStudents")
    public ResponseEntity<List<ApiCourse>> findWithMoreThanNStudents(@RequestParam Integer withMoreThanStudents) {
        return ResponseEntity.ok(
                courseService.findWithMoreThanNStudents(withMoreThanStudents)
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

    @DeleteMapping
    public ResponseEntity<List<ApiCourse>> deleteById(@RequestParam Long id) {
        return ResponseEntity.ok(
                courseService.deleteById(id)
                        .stream()
                        .map(CourseApiConversion::toApi)
                        .toList()
        );
    }

}
