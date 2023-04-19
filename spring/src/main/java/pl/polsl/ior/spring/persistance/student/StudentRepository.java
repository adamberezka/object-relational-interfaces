package pl.polsl.ior.spring.persistance.student;

import pl.polsl.ior.spring.domain.Student;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> getAll();

    List<Student> findByFNameAndSName(String fName, String sName);

    List<Student> findByFNameAndSName(String fName, String sName, int pageSize, int pageNum);

    List<Student> findByFNameLike(String fName);

    List<Student> findDistinctByFName(String fName);

    List<Student> findByFNameOrSName(String fName, String sName);

    List<Student> findWithMedicalTestsContainingIgnoreCase(String medicalTestsContain);

    List<Student> findWithFlightsBetween(OffsetDateTime from, OffsetDateTime to);

    Optional<Student> deleteById(Long id);
}
