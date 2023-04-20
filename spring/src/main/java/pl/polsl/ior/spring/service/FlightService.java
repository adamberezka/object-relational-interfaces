package pl.polsl.ior.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.ior.spring.domain.Flight;
import pl.polsl.ior.spring.persistance.flight.FlightRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getAll() {
        return flightRepository.getAll();
    }

    public List<Flight> findByHoursAndDateBetween(final int hours, final OffsetDateTime from, final OffsetDateTime to){
        return flightRepository.findByHoursAndDateBetween(hours, from, to);
    }

    public List<Flight> findByHoursAndDateBetween(final int hours, final OffsetDateTime from, final OffsetDateTime to, final int pageSize, final int pageNumber){
        return flightRepository.findByHoursAndDateBetween(hours, from, to, pageSize, pageNumber);
    }

    public List<Flight> findByDescriptionLike(final String descriptionLike){
        return flightRepository.findByDescriptionLike(descriptionLike);
    }

    public List<Flight> findDistinctByHours(final int hours){
        return flightRepository.findDistinctByHours(hours);
    }

    public List<Flight> findByHoursOrDateBetween(final int hours, final OffsetDateTime orDateBetweenFrom, final OffsetDateTime orDateBetweenTo){
        return flightRepository.findByHoursOrDateBetween(hours, orDateBetweenFrom, orDateBetweenTo);
    }

    public List<Flight> findWithDescriptionContainingIgnoreCase(final String descriptionContains){
        return flightRepository.findWithDescriptionContainingIgnoreCase(descriptionContains);
    }

    public List<Flight> findLongerThan(final int hours){
        return flightRepository.findLongerThan(hours);
    }

    public Optional<Flight> deleteById(final Long id){
        return flightRepository.deleteById(id);
    }

}
