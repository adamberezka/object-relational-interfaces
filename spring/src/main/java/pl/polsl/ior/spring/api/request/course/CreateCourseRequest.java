package pl.polsl.ior.spring.api.request.course;

import lombok.Value;
import pl.polsl.ior.spring.api.ApiCourse;

@Value
public class CreateCourseRequest {
    ApiCourse course;
}
