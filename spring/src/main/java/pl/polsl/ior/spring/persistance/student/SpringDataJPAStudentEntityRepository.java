package pl.polsl.ior.spring.persistance.student;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.ior.spring.domain.Student;
import pl.polsl.ior.spring.persistance.course.SpringDataJPACourseEntityDAO;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;
import pl.polsl.ior.spring.persistance.flight.conversion.OffsetDateTimeConverter;
import pl.polsl.ior.spring.persistance.student.conversion.StudentConversion;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataJPAStudentEntityRepository implements StudentRepository {

    private final SpringDataJPAStudentEntityDAO studentEntityDAO;
    private final SpringDataJPACourseEntityDAO courseEntityDAO;

    @Override
    public List<Student> getAll() {
        return studentEntityDAO.findAll()
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Override
    public List<Student> findByFNameAndSName(final String fName, final String sName) {
        return studentEntityDAO.findByFirstnameAndSurname(fName, sName)
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Override
    public List<Student> findByFNameAndSName(final String fName,
                                             final String sName,
                                             final int pageSize,
                                             final int pageNum) {
        return studentEntityDAO.findByFirstnameAndSurname(fName, sName, PageRequest.of(pageNum, pageSize))
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Override
    public List<Student> findByFNameLike(final String fName) {
        return studentEntityDAO.findByFirstnameLike(fName)
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Override
    public List<Student> findDistinctByFName(final String fName) {
        return studentEntityDAO.findDistinctByFirstname(fName)
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Override
    public List<Student> findByFNameOrSName(final String fName, final String sName) {
        return studentEntityDAO.findByFirstnameOrSurname(fName, sName)
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Override
    public List<Student> findWithMedicalTestsContainingIgnoreCase(String medicalTestsContain) {
        return studentEntityDAO.findByMedicalTestsContainsIgnoreCase(medicalTestsContain)
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Override
    public List<Student> findWithFlightsBetween(final OffsetDateTime from, final OffsetDateTime to) {
        return studentEntityDAO.findWithFlightsBetween(
                        OffsetDateTimeConverter.toDate(from),
                        OffsetDateTimeConverter.toDate(to))
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Transactional
    @Override
    public Optional<Student> deleteById(final Long id) {
        final Optional<StudentEntity> optionalEntityToDelete = studentEntityDAO.findById(id);
        if (optionalEntityToDelete.isPresent()) {
            final StudentEntity entityToDelete = optionalEntityToDelete.get();
            final List<CourseEntity> coursesWithStudentToDelete = courseEntityDAO.findByStudentsContaining(entityToDelete);
            coursesWithStudentToDelete.forEach(courseEntity -> courseEntity.getStudents().remove(entityToDelete));
            courseEntityDAO.saveAll(coursesWithStudentToDelete);
            studentEntityDAO.delete(entityToDelete);
            return Optional.of(StudentConversion.toDomain(entityToDelete));
        }
        return Optional.empty();
    }
}
