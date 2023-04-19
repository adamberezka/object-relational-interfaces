package pl.polsl.ior.spring.persistance.flight.conversion;

import pl.polsl.ior.spring.domain.Flight;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;

public abstract class FlightConversion {

    public static FlightEntity toEntity(final Flight flight) {
        final FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(flight.getId());
        flightEntity.setDate(OffsetDateTimeConverter.toDate(flight.getDate()));
        flightEntity.setHours(flight.getHours());
        flightEntity.setDescription(flight.getDescription());
        return flightEntity;
    }

    public static Flight toDomain(final FlightEntity flightEntity) {
        return new Flight(
                flightEntity.getId(),
                OffsetDateTimeConverter.toOffsetDateTime(flightEntity.getDate()),
                flightEntity.getHours(),
                flightEntity.getDescription()
        );
    }
}
