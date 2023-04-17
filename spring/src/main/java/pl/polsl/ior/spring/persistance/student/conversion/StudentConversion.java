package pl.polsl.ior.spring.persistance.student.conversion;

import pl.polsl.ior.spring.domain.*;
import pl.polsl.ior.spring.persistance.address.conversion.AddressConversion;
import pl.polsl.ior.spring.persistance.flight.conversion.FlightConversion;
import pl.polsl.ior.spring.persistance.flightinstructor.entity.FlightInstructorEntity;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;
import pl.polsl.ior.spring.persistance.theoryclass.conversion.TheoryClassConversion;

import java.util.stream.Collectors;

public abstract class StudentConversion {

    public static StudentEntity toEntity(final Student student) {
        final StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setFirstname(student.getFName());
        studentEntity.setSurname(student.getSName());
        studentEntity.setSSN(student.getSSN());
        studentEntity.setAddress(AddressConversion.toEntity(student.getAddress()));
        studentEntity.setMedicalTests(student.getMedicalTests());
        studentEntity.setFlights(
                student.getFlights()
                        .stream()
                        .map(FlightConversion::toEntity)
                        .collect(Collectors.toSet())
        );
        studentEntity.setTheoryClasses(
                student.getTheoryClasses()
                        .stream()
                        .map(TheoryClassConversion::toEntity)
                        .collect(Collectors.toSet())
        );
        return studentEntity;
    }

    public static Student toDomain(final StudentEntity studentEntity) {

        final Student student = new Student(
                studentEntity.getId(),
                studentEntity.getFirstname(),
                studentEntity.getSurname(),
                studentEntity.getSSN(),
                AddressConversion.toDomain(studentEntity.getAddress()),
                studentEntity.getMedicalTests(),
                null,
                null
        );

        return student.withTheoryClasses(
                studentEntity.getTheoryClasses()
                        .stream()
                        .map(theoryClassEntity ->
                            new TheoryClass(
                                    theoryClassEntity.getId(),
                                    theoryClassEntity.getName(),
                                    theoryClassEntity.getHours(),
                                    theoryClassEntity.getGrade(),
                                    student
                            )
                        ).collect(Collectors.toSet())
        ).withFlights(
                studentEntity.getFlights()
                        .stream()
                        .map(flightEntity -> {
                                    final Flight flight = new Flight(
                                            flightEntity.getId(),
                                            flightEntity.getDate(),
                                            flightEntity.getHours(),
                                            flightEntity.getDescription(),
                                            null,
                                            student
                                    );
                                    final FlightInstructorEntity flightInstructorEntity = flightEntity.getFlightInstructor();
                                    final FlightInstructor flightInstructor = new FlightInstructor(
                                            flightInstructorEntity.getId(),
                                            flightInstructorEntity.getFirstname(),
                                            flightInstructorEntity.getSurname(),
                                            flightInstructorEntity.getSSN(),
                                            AddressConversion.toDomain(flightInstructorEntity.getAddress()),
                                            flightInstructorEntity.getLicenceNo(),
                                            flightInstructorEntity.isValid()
                                    );
                                    return flight.withFlightInstructor(flightInstructor);
                                }
                        )
                        .collect(Collectors.toSet())
        );
    }


}

/*

studentEntity.getFlights()
                        .stream()
                        .map(FlightConversion::toDomain)
                        .collect(Collectors.toSet()),
                studentEntity.getTheoryClasses()
                        .stream()
                        .map(TheoryClassConversion::toDomain)
                        .collect(Collectors.toSet()),
                studentEntity.getCourses()
                        .stream()
                        .map(CourseConversion::toDomain)
                        .collect(Collectors.toSet())

* */
