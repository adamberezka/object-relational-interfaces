package pl.polsl.ior.spring.persistance.flightinstructor.conversion;

import pl.polsl.ior.spring.domain.FlightInstructor;
import pl.polsl.ior.spring.persistance.address.conversion.AddressConversion;
import pl.polsl.ior.spring.persistance.flight.conversion.FlightConversion;
import pl.polsl.ior.spring.persistance.flightinstructor.entity.FlightInstructorEntity;

import java.util.stream.Collectors;

public abstract class FlightInstructorConversion {

    public static FlightInstructorEntity toEntity(final FlightInstructor flightInstructor) {
        final FlightInstructorEntity flightInstructorEntity = new FlightInstructorEntity();
        flightInstructorEntity.setId(flightInstructor.getId());
        flightInstructorEntity.setFName(flightInstructor.getFName());
        flightInstructorEntity.setSName(flightInstructor.getSName());
        flightInstructorEntity.setSSN(flightInstructor.getSSN());
        flightInstructorEntity.setAddress(AddressConversion.toEntity(flightInstructor.getAddress()));
        flightInstructorEntity.setLicenceNo(flightInstructor.getLicenceNo());
        flightInstructorEntity.setValid(flightInstructor.isValid());
        flightInstructorEntity.setFlights(
                flightInstructor.getFlights()
                        .stream()
                        .map(FlightConversion::toEntity)
                        .collect(Collectors.toSet())
        );
        return flightInstructorEntity;
    }

    public static FlightInstructor toDomain(final FlightInstructorEntity flightInstructorEntity) {
        return new FlightInstructor(
                flightInstructorEntity.getId(),
                flightInstructorEntity.getFName(),
                flightInstructorEntity.getSName(),
                flightInstructorEntity.getSSN(),
                AddressConversion.toDomain(flightInstructorEntity.getAddress()),
                flightInstructorEntity.getLicenceNo(),
                flightInstructorEntity.isValid(),
                flightInstructorEntity.getFlights()
                        .stream()
                        .map(FlightConversion::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}
