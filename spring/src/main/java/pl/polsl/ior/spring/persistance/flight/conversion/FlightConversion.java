package pl.polsl.ior.spring.persistance.flight.conversion;

import pl.polsl.ior.spring.domain.Flight;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;
import pl.polsl.ior.spring.persistance.flightinstructor.conversion.FlightInstructorConversion;
import pl.polsl.ior.spring.persistance.student.conversion.StudentConversion;

public abstract class FlightConversion {

    public static FlightEntity toEntity(final Flight flight) {
        final FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(flight.id());
        flightEntity.setDate(flight.date());
        flightEntity.setHours(flight.hours());
        flightEntity.setDescription(flight.description());
        flightEntity.setFlightInstructor(FlightInstructorConversion.toEntity(flight.flightInstructor()));
        flightEntity.setStudent(StudentConversion.toEntity(flight.student()));
        return flightEntity;
    }

    public static Flight toDomain(final FlightEntity flightEntity) {
        return new Flight(
                flightEntity.getId(),
                flightEntity.getDate(),
                flightEntity.getHours(),
                flightEntity.getDescription(),
                FlightInstructorConversion.toDomain(flightEntity.getFlightInstructor()),
                StudentConversion.toDomain(flightEntity.getStudent())
        );
    }
}
