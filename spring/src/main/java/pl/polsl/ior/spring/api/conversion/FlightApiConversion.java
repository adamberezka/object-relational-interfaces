package pl.polsl.ior.spring.api.conversion;

import pl.polsl.ior.spring.api.ApiFlight;
import pl.polsl.ior.spring.domain.Flight;

public abstract class FlightApiConversion {

    public static ApiFlight toApi(final Flight flight) {
        return new ApiFlight(
                flight.getId(),
                flight.getDate(),
                flight.getHours(),
                flight.getDescription()
        );
    }
}
