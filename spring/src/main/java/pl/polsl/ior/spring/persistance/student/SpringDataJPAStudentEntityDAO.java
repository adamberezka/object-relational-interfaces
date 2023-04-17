package pl.polsl.ior.spring.persistance.student;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface SpringDataJPAStudentEntityDAO extends JpaRepository<StudentEntity, Long> {


    List<StudentEntity> findByFirstnameAndSurname(String fName, String sName);

    List<StudentEntity> findByFirstnameAndSurname(String fName, String sName, Pageable pageable);

    List<StudentEntity> findByFirstnameLike(String fName);

    Optional<StudentEntity> findDistinctByFirstname(String fName);

    List<StudentEntity> findByFirstnameOrSurname(String FName, String sName);

    @Query("SELECT StudentEntity FROM StudentEntity se " +
            "WHERE (SELECT count(f) FROM se.flights f WHERE f.date between ?1 AND ?2) >= 0")
    List<StudentEntity> findWithFlightsBetween(OffsetDateTime from, OffsetDateTime to);

}
