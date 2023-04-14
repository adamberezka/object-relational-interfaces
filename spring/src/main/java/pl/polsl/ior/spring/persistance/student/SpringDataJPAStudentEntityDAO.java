package pl.polsl.ior.spring.persistance.student;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

public interface SpringDataJPAStudentEntityDAO extends JpaRepository<StudentEntity, Long> {
}
