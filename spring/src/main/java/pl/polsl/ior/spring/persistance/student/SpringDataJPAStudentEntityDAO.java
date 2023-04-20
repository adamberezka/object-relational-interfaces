package pl.polsl.ior.spring.persistance.student;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

import java.sql.Date;
import java.util.List;

public interface SpringDataJPAStudentEntityDAO extends JpaRepository<StudentEntity, Long> {


    List<StudentEntity> findByFirstnameAndSurname(String fName, String sName);

    List<StudentEntity> findByFirstnameAndSurname(String fName, String sName, Pageable pageable);

    List<StudentEntity> findByFirstnameLike(String fName);

    List<StudentEntity> findDistinctByFirstname(String fName);

    List<StudentEntity> findByMedicalTestsContainsIgnoreCase(String medicalTestsContain);

    List<StudentEntity> findByFirstnameOrSurname(String FName, String sName);

    @Query("SELECT se FROM StudentEntity se " +
            "WHERE (SELECT count(f) FROM se.flights f WHERE f.date BETWEEN ?1 AND ?2) >= 1")
    List<StudentEntity> findWithFlightsBetween(Date from, Date to);

}
