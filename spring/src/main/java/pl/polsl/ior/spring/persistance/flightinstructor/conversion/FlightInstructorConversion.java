package pl.polsl.ior.spring.persistance.flightinstructor.conversion;

import pl.polsl.ior.spring.domain.FlightInstructor;
import pl.polsl.ior.spring.persistance.address.conversion.AddressConversion;
import pl.polsl.ior.spring.persistance.flightinstructor.entity.FlightInstructorEntity;

public abstract class FlightInstructorConversion {

    public static FlightInstructorEntity toEntity(final FlightInstructor flightInstructor) {
        final FlightInstructorEntity flightInstructorEntity = new FlightInstructorEntity();
        flightInstructorEntity.setId(flightInstructor.getId());
        flightInstructorEntity.setFirstname(flightInstructor.getFName());
        flightInstructorEntity.setSurname(flightInstructor.getSName());
        flightInstructorEntity.setSSN(flightInstructor.getSSN());
        flightInstructorEntity.setAddress(AddressConversion.toEntity(flightInstructor.getAddress()));
        flightInstructorEntity.setLicenceNo(flightInstructor.getLicenceNo());
        flightInstructorEntity.setValid(flightInstructor.isValid());
        return flightInstructorEntity;
    }

    public static FlightInstructor toDomain(final FlightInstructorEntity flightInstructorEntity) {
        return new FlightInstructor(
                flightInstructorEntity.getId(),
                flightInstructorEntity.getFirstname(),
                flightInstructorEntity.getSurname(),
                flightInstructorEntity.getSSN(),
                AddressConversion.toDomain(flightInstructorEntity.getAddress()),
                flightInstructorEntity.getLicenceNo(),
                flightInstructorEntity.isValid()
        );
    }
}
