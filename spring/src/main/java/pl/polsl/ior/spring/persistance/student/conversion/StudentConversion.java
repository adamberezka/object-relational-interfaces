package pl.polsl.ior.spring.persistance.student.conversion;

import pl.polsl.ior.spring.domain.Student;
import pl.polsl.ior.spring.persistance.address.conversion.AddressConversion;
import pl.polsl.ior.spring.persistance.course.conversion.CourseConversion;
import pl.polsl.ior.spring.persistance.flight.conversion.FlightConversion;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;
import pl.polsl.ior.spring.persistance.theoryclass.conversion.TheoryClassConversion;

import java.util.stream.Collectors;

public abstract class StudentConversion {

    public static StudentEntity toEntity(final Student student) {
        final StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setFName(student.getFName());
        studentEntity.setSName(student.getSName());
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
        studentEntity.setCourses(
                student.getCourses()
                        .stream()
                        .map(CourseConversion::toEntity)
                        .collect(Collectors.toSet())
        );
        return studentEntity;
    }

    public static Student toDomain(final StudentEntity studentEntity) {
        return new Student(
                studentEntity.getId(),
                studentEntity.getFName(),
                studentEntity.getSName(),
                studentEntity.getSSN(),
                AddressConversion.toDomain(studentEntity.getAddress()),
                studentEntity.getMedicalTests(),
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
        );
    }
}
