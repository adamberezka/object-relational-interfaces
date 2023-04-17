package pl.polsl.ior.spring.persistance.course;

import pl.polsl.ior.spring.domain.Course;

public interface CourseRepository {
    Course save(final Course course);
}
