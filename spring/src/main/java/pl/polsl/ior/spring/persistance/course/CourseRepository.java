package pl.polsl.ior.spring.persistance.course;

import pl.polsl.ior.spring.domain.Course;

public interface CourseRepository {
    public Course save(final Course course);
}
