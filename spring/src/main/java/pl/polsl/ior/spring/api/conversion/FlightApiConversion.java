package pl.polsl.ior.spring.api.conversion;

import pl.polsl.ior.spring.api.ApiFlight;
import pl.polsl.ior.spring.api.ApiFlightInstructor;
import pl.polsl.ior.spring.domain.Flight;

import java.util.Set;

public abstract class FlightApiConversion {

    public static ApiFlight toApi(final Flight flight) {
        return new ApiFlight(
                flight.getId(),
                flight.getDate(),
                flight.getHours(),
                flight.getDescription(),
                null,
                null
        );
    }
}
