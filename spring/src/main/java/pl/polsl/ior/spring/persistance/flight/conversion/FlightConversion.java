package pl.polsl.ior.spring.persistance.flight.conversion;

import pl.polsl.ior.spring.domain.Flight;
import pl.polsl.ior.spring.domain.Student;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;
import pl.polsl.ior.spring.persistance.flightinstructor.conversion.FlightInstructorConversion;
import pl.polsl.ior.spring.persistance.student.conversion.StudentConversion;

public abstract class FlightConversion {

    public static FlightEntity toEntity(final Flight flight) {
        final FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(flight.getId());
        flightEntity.setDate(flight.getDate());
        flightEntity.setHours(flight.getHours());
        flightEntity.setDescription(flight.getDescription());
        flightEntity.setFlightInstructor(FlightInstructorConversion.toEntity(flight.getFlightInstructor()));
        flightEntity.setStudent(StudentConversion.toEntity(flight.getStudent()));
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

    public static Flight toDomain(final FlightEntity flightEntity, final Student student) {
        return new Flight(
                flightEntity.getId(),
                flightEntity.getDate(),
                flightEntity.getHours(),
                flightEntity.getDescription(),
                FlightInstructorConversion.toDomain(flightEntity.getFlightInstructor()),
                student
        );
    }
}
