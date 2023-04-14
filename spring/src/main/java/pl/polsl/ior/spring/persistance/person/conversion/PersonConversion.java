package pl.polsl.ior.spring.persistance.person.conversion;

import pl.polsl.ior.spring.domain.FlightInstructor;
import pl.polsl.ior.spring.domain.Person;
import pl.polsl.ior.spring.domain.Student;
import pl.polsl.ior.spring.persistance.flightinstructor.conversion.FlightInstructorConversion;
import pl.polsl.ior.spring.persistance.flightinstructor.entity.FlightInstructorEntity;
import pl.polsl.ior.spring.persistance.person.entity.PersonEntity;
import pl.polsl.ior.spring.persistance.student.conversion.StudentConversion;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

public abstract class PersonConversion {

    public static PersonEntity toEntity(final Person person) {
        return switch (person) {
            case Student student -> StudentConversion.toEntity(student);
            case FlightInstructor flightInstructor -> FlightInstructorConversion.toEntity(flightInstructor);
            default -> throw new IllegalStateException("Unexpected value: " + person.getClass().getSimpleName());
        };
    }

    public static Person toDomain(final PersonEntity personEntity) {
        return switch (personEntity) {
            case StudentEntity studentEntity -> StudentConversion.toDomain(studentEntity);
            case FlightInstructorEntity flightInstructorEntity -> FlightInstructorConversion.toDomain(flightInstructorEntity);
            default -> throw new IllegalStateException("Unexpected value: " + personEntity.getClass().getSimpleName());
        };
    }
}
