package pl.polsl.ior.spring.persistance.flightinstructor;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.ior.spring.persistance.flightinstructor.entity.FlightInstructorEntity;

public interface SpringDataJPAFlightInstructorEntityDAO extends JpaRepository<FlightInstructorEntity, Long> {
}
