package pl.polsl.ior.spring.persistance.course.conversion;

import pl.polsl.ior.spring.domain.Course;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;
import pl.polsl.ior.spring.persistance.student.conversion.StudentConversion;

import java.util.stream.Collectors;

public abstract class CourseConversion {

    public static CourseEntity toEntity(final Course course) {
        final CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(course.id());
        courseEntity.setCertType(course.certType());
        courseEntity.setStartDate(course.startDate());
        courseEntity.setEndDate(course.endDate());
        courseEntity.setDescription(course.description());
        courseEntity.setStudents(
                course.students()
                        .stream()
                        .map(StudentConversion::toEntity)
                        .collect(Collectors.toSet())
        );
        return courseEntity;
    }

    public static Course toDomain(final CourseEntity courseEntity) {
        return new Course(
                courseEntity.getId(),
                courseEntity.getCertType(),
                courseEntity.getStartDate(),
                courseEntity.getEndDate(),
                courseEntity.getDescription(),
                courseEntity.getStudents()
                        .stream()
                        .map(StudentConversion::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}
