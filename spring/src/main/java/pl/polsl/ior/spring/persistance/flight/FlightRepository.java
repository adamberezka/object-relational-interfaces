package pl.polsl.ior.spring.persistance.flight;

import pl.polsl.ior.spring.domain.Flight;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    List<Flight> getAll();

    List<Flight> findByHoursAndDateBetween(int hours, OffsetDateTime from, OffsetDateTime to);

    List<Flight> findByHoursAndDateBetween(int hours, OffsetDateTime from, OffsetDateTime to, int pageSize, int pageNumber);

    List<Flight> findByDescriptionLike(String descriptionLike);

    List<Flight> findDistinctByHours(int hours);

    List<Flight> findByHoursOrDateBetween(int hours, OffsetDateTime orDateBetweenFrom, OffsetDateTime orDateBetweenTo);

    List<Flight> findWithDescriptionContainingIgnoreCase(String descriptionContains);

    List<Flight> findLongerThan(int hours);

    Optional<Flight> deleteById(Long id);
}
