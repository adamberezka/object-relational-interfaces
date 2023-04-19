package pl.polsl.ior.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.ior.spring.domain.Student;
import pl.polsl.ior.spring.persistance.student.StudentRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    public List<Student> findByFNameAndSName(final String fName, final String sName) {
        return studentRepository.findByFNameAndSName(fName, sName);
    }

    public List<Student> findByFNameAndSName(final String fName, final String sName, final int pageSize, final int pageNum) {
        return studentRepository.findByFNameAndSName(fName, sName, pageSize, pageNum);
    }

    public List<Student> findByFNameLike(final String fName) {
        return studentRepository.findByFNameLike(fName);
    }

    public List<Student> findDistinctByFName(final String fName) {
        return studentRepository.findDistinctByFName(fName);
    }

    public List<Student> findByFNameOrSName(final String fName, final String sName) {
        return studentRepository.findByFNameOrSName(fName, sName);
    }

    public List<Student> findByMedicalTestsContaining(final String medicalTestsContain) {
        return studentRepository.findWithMedicalTestsContainingIgnoreCase(medicalTestsContain);
    }

    public List<Student> findWithFlightsBetween(final OffsetDateTime from, final OffsetDateTime to) {
        return studentRepository.findWithFlightsBetween(from, to);
    }

    public Optional<Student> deleteById(final Long id) {
        return studentRepository.deleteById(id);
    }




}
