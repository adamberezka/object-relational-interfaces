package pl.polsl.ior.spring.persistance.course;

import pl.polsl.ior.spring.domain.Course;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    List<Course> getAll();

    List<Course> findByCertTypeAndStartDateBetween(String certType, OffsetDateTime startDateFrom, OffsetDateTime startDateTo);

    List<Course> findByCertTypeAndStartDateBetween(String certType, OffsetDateTime startDateFrom, OffsetDateTime startDateTo, int pageSize, int pageNumber);

    List<Course> findByCertTypeLike(String certTypeLike);

    List<Course> findDistinctByCertType(String certType);

    List<Course> findByCertTypeOrStartDateBetween(String certType, OffsetDateTime startDateFrom, OffsetDateTime startDateTo);

    List<Course> findByDescriptionContainingIgnoreCase(String descriptionContains);

    List<Course> findWithMoreThanNStudents(int amountOfStudents);

    Optional<Course> deleteById(Long id);
}
