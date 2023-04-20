package pl.polsl.ior.spring.api.conversion;

import pl.polsl.ior.spring.api.ApiCourse;
import pl.polsl.ior.spring.domain.Course;

import java.util.stream.Collectors;

public abstract class CourseApiConversion {

    public static ApiCourse toApi(final Course course) {
        return new ApiCourse(
                course.id(),
                course.certType(),
                course.startDate(),
                course.endDate(),
                course.description(),
                course.students()
                        .stream()
                        .map(StudentApiConversion::toApi)
                        .collect(Collectors.toSet())
        );
    }

}
