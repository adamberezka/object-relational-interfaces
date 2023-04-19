package pl.polsl.ior.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiCourse;
import pl.polsl.ior.spring.api.conversion.CourseApiConversion;
import pl.polsl.ior.spring.api.request.course.CreateCourseRequest;
import pl.polsl.ior.spring.api.response.course.CreateCourseResponse;
import pl.polsl.ior.spring.domain.Course;
import pl.polsl.ior.spring.service.CourseService;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.Course.ENDPOINT)
public class CourseController {

    private final CourseService courseService;
}
