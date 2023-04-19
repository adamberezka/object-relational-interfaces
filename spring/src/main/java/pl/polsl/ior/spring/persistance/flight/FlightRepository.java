package pl.polsl.ior.spring.persistance.flight;

import pl.polsl.ior.spring.domain.Flight;

import java.util.List;

public interface FlightRepository {

    List<Flight> getAll();

}
