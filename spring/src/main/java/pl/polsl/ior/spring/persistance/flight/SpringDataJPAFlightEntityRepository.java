package pl.polsl.ior.spring.persistance.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.ior.spring.domain.Flight;
import pl.polsl.ior.spring.persistance.flight.conversion.FlightConversion;
import pl.polsl.ior.spring.persistance.flight.conversion.OffsetDateTimeConverter;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataJPAFlightEntityRepository implements FlightRepository {

    private final SpringDataJPAFlightEntityDAO flightEntityDAO;

    @Override
    public List<Flight> getAll() {
        return flightEntityDAO.findAll()
                .stream()
                .map(FlightConversion::toDomain)
                .toList();
    }

    @Override
    public List<Flight> findByHoursAndDateBetween(int hours, OffsetDateTime from, OffsetDateTime to) {
        return flightEntityDAO.findByHoursAndDateBetween(
                        hours,
                        OffsetDateTimeConverter.toDate(from),
                        OffsetDateTimeConverter.toDate(to))
                .stream()
                .map(FlightConversion::toDomain)
                .toList();
    }

    @Override
    public List<Flight> findByHoursAndDateBetween(int hours, OffsetDateTime from, OffsetDateTime to, int pageSize, int pageNumber) {
        return flightEntityDAO.findByHoursAndDateBetween(
                        hours,
                        OffsetDateTimeConverter.toDate(from),
                        OffsetDateTimeConverter.toDate(to),
                        PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(FlightConversion::toDomain)
                .toList();
    }

    @Override
    public List<Flight> findByDescriptionLike(String descriptionLike) {
        return flightEntityDAO.findByDescriptionLike(descriptionLike)
                .stream()
                .map(FlightConversion::toDomain)
                .toList();
    }

    @Override
    public List<Flight> findDistinctByHours(int hours) {
        return flightEntityDAO.findDistinctByHours(hours)
                .stream()
                .map(FlightConversion::toDomain)
                .toList();
    }

    @Override
    public List<Flight> findByHoursOrDateBetween(int hours, OffsetDateTime orDateBetweenFrom, OffsetDateTime orDateBetweenTo) {
        return flightEntityDAO.findByHoursOrDateBetween(
                        hours,
                        OffsetDateTimeConverter.toDate(orDateBetweenFrom),
                        OffsetDateTimeConverter.toDate(orDateBetweenTo))
                .stream()
                .map(FlightConversion::toDomain)
                .toList();
    }

    @Override
    public List<Flight> findWithDescriptionContainingIgnoreCase(String descriptionContains) {
        return flightEntityDAO.findByDescriptionContainingIgnoreCase(descriptionContains)
                .stream()
                .map(FlightConversion::toDomain)
                .toList();
    }

    @Override
    public List<Flight> findLongerThan(int hours) {
        return flightEntityDAO.findLongerThan(hours)
                .stream()
                .map(FlightConversion::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public Optional<Flight> deleteById(Long id) {
        final Optional<FlightEntity> optionalEntityToDelete = flightEntityDAO.findById(id);
        if(optionalEntityToDelete.isPresent()) {
            final FlightEntity entityToDelete = optionalEntityToDelete.get();
            flightEntityDAO.delete(entityToDelete);
        }
        return optionalEntityToDelete.map(FlightConversion::toDomain);
    }
}
