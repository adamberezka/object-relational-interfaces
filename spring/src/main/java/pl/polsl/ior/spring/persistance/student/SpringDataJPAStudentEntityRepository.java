package pl.polsl.ior.spring.persistance.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import pl.polsl.ior.spring.domain.Student;
import pl.polsl.ior.spring.persistance.student.conversion.StudentConversion;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataJPAStudentEntityRepository implements StudentRepository {

    private final SpringDataJPAStudentEntityDAO studentEntityDAO;

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
                                             final  int pageSize,
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
    public Optional<Student> findDistinctByFName(final String fName) {
        return studentEntityDAO.findDistinctByFirstname(fName)
                .map(StudentConversion::toDomain);
    }

    @Override
    public List<Student> findByFNameOrSName(final String fName, final String sName) {
        return studentEntityDAO.findByFirstnameOrSurname(fName, sName)
                .stream()
                .map(StudentConversion::toDomain)
                .toList();
    }

    @Override
    public List<Student> findWithFlightsBetween(OffsetDateTime from, OffsetDateTime to) {
        return null;
    }
}
