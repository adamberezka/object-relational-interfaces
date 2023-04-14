package pl.polsl.ior.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.ior.spring.domain.Course;
import pl.polsl.ior.spring.persistance.course.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public Course createCourse(final Course course) {
        return repository.save(course);
    }
}
