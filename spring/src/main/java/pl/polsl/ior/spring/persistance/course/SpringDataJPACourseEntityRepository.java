package pl.polsl.ior.spring.persistance.course;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import pl.polsl.ior.spring.domain.Course;
import pl.polsl.ior.spring.persistance.course.conversion.CourseConversion;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SpringDataJPACourseEntityRepository implements CourseRepository {

    private final SpringDataJPACourseEntityDAO courseDao;

    @Override
    public List<Course> getAll() {
        return courseDao.findAll()
                .stream()
                .map(CourseConversion::toDomain)
                .toList();
    }

    @Override
    public List<Course> findByCertTypeAndStartDateBetween(String certType, OffsetDateTime startDateFrom, OffsetDateTime startDateTo) {
        return courseDao.findByCertTypeAndStartDateBetween(certType, startDateFrom, startDateTo)
                .stream()
                .map(CourseConversion::toDomain)
                .toList();
    }

    @Override
    public List<Course> findByCertTypeAndStartDateBetween(String certType, OffsetDateTime startDateFrom, OffsetDateTime startDateTo, int pageSize, int pageNumber) {
        return courseDao.findByCertTypeAndStartDateBetween(certType,
                        startDateFrom,
                        startDateTo,
                        PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(CourseConversion::toDomain)
                .toList();
    }

    @Override
    public List<Course> findByCertTypeLike(String certTypeLike) {
        return courseDao.findByCertTypeLike(certTypeLike)
                .stream()
                .map(CourseConversion::toDomain)
                .toList();
    }

    @Override
    public List<Course> findDistinctByCertType(String certType) {
        return courseDao.findDistinctByCertType(certType)
                .stream()
                .map(CourseConversion::toDomain)
                .toList();
    }

    @Override
    public List<Course> findByCertTypeOrStartDateBetween(String certType, OffsetDateTime startDateFrom, OffsetDateTime startDateTo) {
        return courseDao.findByCertTypeOrStartDateBetween(certType, startDateFrom, startDateTo)
                .stream()
                .map(CourseConversion::toDomain)
                .toList();
    }

    @Override
    public List<Course> findByDescriptionContainingIgnoreCase(String descriptionContains) {
        return courseDao.findByDescriptionContainingIgnoreCase(descriptionContains)
                .stream()
                .map(CourseConversion::toDomain)
                .toList();
    }

    @Override
    public List<Course> findWithMoreThanNStudents(int amountOfStudents) {
        return courseDao.findWithMoreThanStudents(amountOfStudents)
                .stream()
                .map(CourseConversion::toDomain)
                .toList();
    }

    @Override
    public Optional<Course> deleteById(Long id) {
        final Optional<CourseEntity> optionalEntityToDelete = courseDao.findById(id);
        if (optionalEntityToDelete.isPresent()) {
            final CourseEntity entityToDelete = optionalEntityToDelete.get();
            entityToDelete.setStudents(Set.of());
            courseDao.delete(entityToDelete);
        }
        return optionalEntityToDelete.map(CourseConversion::toDomain);
    }
}
