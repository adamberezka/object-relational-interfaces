package pl.polsl.ior.spring.api.conversion;

import pl.polsl.ior.spring.api.ApiCourse;
import pl.polsl.ior.spring.domain.Course;

public abstract class CourseApiConversion {

    public static ApiCourse toApi(final Course course) {
        return new ApiCourse(
                course.id(),
                course.certType(),
                course.startDate(),
                course.endDate(),
                course.description()
        );
    }

    public static Course toDomain(final ApiCourse apiCourse) {
        return new Course(
                apiCourse.getId(),
                apiCourse.getCertType(),
                apiCourse.getStartDate(),
                apiCourse.getEndDate(),
                apiCourse.getDescription(),
                null // apiCourse.getStudents()
        );
    }

}
