package pl.polsl.ior.spring.persistance.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.polsl.ior.spring.domain.Flight;
import pl.polsl.ior.spring.persistance.flight.conversion.FlightConversion;

import java.util.List;

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
}
