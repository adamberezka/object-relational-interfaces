package pl.polsl.ior.spring.persistance.flight;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;

import java.sql.Date;
import java.util.List;

public interface SpringDataJPAFlightEntityDAO extends JpaRepository<FlightEntity, Long> {

    List<FlightEntity> findByHoursAndDateBetween(Integer hours, Date from, Date to);

    List<FlightEntity> findByHoursAndDateBetween(Integer hours, Date from, Date to, Pageable pageable);

    List<FlightEntity> findByDescriptionLike(String descriptionLike);

    List<FlightEntity> findDistinctByHours(Integer hours);

    List<FlightEntity> findByHoursOrDateBetween(Integer hours, Date from, Date to);

    List<FlightEntity> findByDescriptionContainingIgnoreCase(String descriptionContains);

    @Query("SELECT f FROM FlightEntity f " +
            "WHERE f.hours > ?1")
    List<FlightEntity> findLongerThan(Integer hours);
}
