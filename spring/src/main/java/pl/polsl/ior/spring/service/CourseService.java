package pl.polsl.ior.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.ior.spring.domain.Course;
import pl.polsl.ior.spring.persistance.course.CourseRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public List<Course> getAll() {
        return repository.getAll();
    }

    public List<Course> findByCertTypeAndStartDateBetween(final String certType,
                                                          final OffsetDateTime startDateFrom,
                                                          final OffsetDateTime startDateTo) {
        return repository.findByCertTypeAndStartDateBetween(certType, startDateFrom, startDateTo);
    }

    public List<Course> findByCertTypeAndStartDateBetween(final String certType,
                                                          final OffsetDateTime startDateFrom,
                                                          final OffsetDateTime startDateTo,
                                                          final int pageSize,
                                                          final int pageNumber) {
        return repository.findByCertTypeAndStartDateBetween(certType, startDateFrom, startDateTo, pageSize, pageNumber);
    }

    public List<Course> findByCertTypeLike(final String certTypeLike) {
        return repository.findByCertTypeLike(certTypeLike);
    }

    public List<Course> findDistinctByCertType(final String certType) {
        return repository.findDistinctByCertType(certType);
    }

    public List<Course> findByCertTypeOrStartDateBetween(final String certType,
                                                         final OffsetDateTime startDateFrom,
                                                         final OffsetDateTime startDateTo) {
        return repository.findByCertTypeOrStartDateBetween(certType, startDateFrom, startDateTo);
    }

    public List<Course> findByDescriptionContainingIgnoreCase(final String descriptionContains) {
        return repository.findByDescriptionContainingIgnoreCase(descriptionContains);
    }

    public List<Course> findWithMoreThanNStudents(final int amountOfStudents) {
        return repository.findWithMoreThanNStudents(amountOfStudents);
    }

    public Optional<Course> deleteById(final Long id) {
        return repository.deleteById(id);
    }

}
