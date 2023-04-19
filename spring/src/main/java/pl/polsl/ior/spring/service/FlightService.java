package pl.polsl.ior.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.ior.spring.domain.Flight;
import pl.polsl.ior.spring.persistance.flight.FlightRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getAll() {
        return flightRepository.getAll();
    }

}
