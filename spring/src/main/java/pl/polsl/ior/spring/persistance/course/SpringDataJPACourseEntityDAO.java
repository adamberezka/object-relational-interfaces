package pl.polsl.ior.spring.persistance.course;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

import java.util.List;

public interface SpringDataJPACourseEntityDAO extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findByStudentsContaining(final StudentEntity studentEntity);

}
