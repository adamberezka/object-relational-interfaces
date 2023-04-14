package pl.polsl.ior.spring.persistance.course;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;


public interface SpringDataJPACourseEntityDAO extends JpaRepository<CourseEntity, Long> {

}
