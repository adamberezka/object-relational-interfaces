package pl.polsl.ior.spring.api.response.course;

import lombok.Value;
import pl.polsl.ior.spring.api.ApiCourse;

@Value
public class CreateCourseResponse {
    ApiCourse course;
}
