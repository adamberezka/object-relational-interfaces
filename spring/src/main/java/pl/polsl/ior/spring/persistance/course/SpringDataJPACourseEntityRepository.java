package pl.polsl.ior.spring.persistance.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.polsl.ior.spring.domain.Course;
import pl.polsl.ior.spring.persistance.course.conversion.CourseConversion;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;

@Repository
@RequiredArgsConstructor
public class SpringDataJPACourseEntityRepository implements CourseRepository {

    private final SpringDataJPACourseEntityDAO courseDao;

    @Override
    public Course save(final Course course) {
        final CourseEntity entityToSave = CourseConversion.toEntity(course);
        final CourseEntity savedEntity = courseDao.save(entityToSave);
        return CourseConversion.toDomain(savedEntity);
    }
}
