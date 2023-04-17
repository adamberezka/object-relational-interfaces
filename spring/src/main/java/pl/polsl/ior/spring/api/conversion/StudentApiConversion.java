package pl.polsl.ior.spring.api.conversion;

import pl.polsl.ior.spring.api.ApiStudent;
import pl.polsl.ior.spring.domain.Student;

import java.util.Set;

public abstract class StudentApiConversion {

    public static ApiStudent toApi(final Student student) {
        return new ApiStudent(
                student.getId(),
                student.getFName(),
                student.getSName(),
                student.getSSN(),
                AddressApiConversion.toApi(student.getAddress()),
                student.getMedicalTests(),
                Set.of(),
                Set.of()
        );
    }

}
