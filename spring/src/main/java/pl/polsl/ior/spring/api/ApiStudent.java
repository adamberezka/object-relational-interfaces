package pl.polsl.ior.spring.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiStudent extends ApiPerson {
    String medicalTests;
    Set<ApiFlight> flights;
    Set<ApiTheoryClass> theoryClasses;

    public ApiStudent(Long id,
                      String fName,
                      String sName,
                      String SSN,
                      ApiAddress address,
                      String medicalTests,
                      Set<ApiFlight> flights,
                      Set<ApiTheoryClass> theoryClasses) {
        super(id, fName, sName, SSN, address);
        this.medicalTests = medicalTests;
        this.flights = flights;
        this.theoryClasses = theoryClasses;
    }
}
