package pl.polsl.ior.spring.persistance.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;

public interface SpringDataJPAFlightEntityDAO extends JpaRepository<FlightEntity, Long> {
}
