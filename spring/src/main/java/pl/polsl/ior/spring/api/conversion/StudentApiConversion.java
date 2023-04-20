package pl.polsl.ior.spring.api.conversion;

import pl.polsl.ior.spring.api.ApiStudent;
import pl.polsl.ior.spring.domain.Student;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class StudentApiConversion {

    public static ApiStudent toApi(final Student student) {
        return new ApiStudent(
                student.getId(),
                student.getFName(),
                student.getSName(),
                student.getSSN(),
                AddressApiConversion.toApi(student.getAddress()),
                student.getMedicalTests(),
                student.getFlights()
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .collect(Collectors.toSet()),
                student.getTheoryClasses()
                        .stream()
                        .map(TheoryClassApiConversion::toApi)
                        .collect(Collectors.toSet())
        );
    }

}
