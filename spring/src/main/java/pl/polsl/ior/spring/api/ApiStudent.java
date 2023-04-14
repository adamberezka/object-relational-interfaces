package pl.polsl.ior.spring.api;

import lombok.Value;

import java.util.Set;

@Value
public class ApiStudent extends ApiPerson {
    String medicalTests;
    Set<ApiFlight> flights;
    Set<ApiTheoryClass> theoryClasses;
    Set<ApiCourse> courses;

    public ApiStudent(Long id,
                      String fName,
                      String sName,
                      String SSN,
                      ApiAddress address,
                      String medicalTests,
                      Set<ApiFlight> flights,
                      Set<ApiTheoryClass> theoryClasses,
                      Set<ApiCourse> courses) {
        super(id, fName, sName, SSN, address);
        this.medicalTests = medicalTests;
        this.flights = flights;
        this.theoryClasses = theoryClasses;
        this.courses = courses;
    }
}