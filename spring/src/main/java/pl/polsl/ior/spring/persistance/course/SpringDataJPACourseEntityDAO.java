package pl.polsl.ior.spring.persistance.course;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface SpringDataJPACourseEntityDAO extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findByStudentsContaining(final StudentEntity studentEntity);

    List<CourseEntity> findByCertTypeAndStartDateBetween(String certType, OffsetDateTime from, OffsetDateTime to);

    List<CourseEntity> findByCertTypeAndStartDateBetween(String certType, OffsetDateTime from, OffsetDateTime to, Pageable pageable);

    List<CourseEntity> findByCertTypeLike(String certTypeLike);

    List<CourseEntity> findDistinctByCertType(String certType);

    List<CourseEntity> findByCertTypeOrStartDateBetween(String certType, OffsetDateTime from, OffsetDateTime to);

    List<CourseEntity> findByDescriptionContainingIgnoreCase(String descriptionContains);

    @Query("SELECT c FROM CourseEntity c " +
            "WHERE (SELECT count(s) FROM c.students s) > ?1")
    List<CourseEntity> findWithMoreThanStudents(Integer amountOfStudents);
}
