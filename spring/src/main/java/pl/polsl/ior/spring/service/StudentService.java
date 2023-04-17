package pl.polsl.ior.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.ior.spring.domain.Student;
import pl.polsl.ior.spring.persistance.student.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.getAll();
    }

}
