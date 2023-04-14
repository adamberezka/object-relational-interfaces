package pl.polsl.ior.spring.domain;

import lombok.Value;

import java.util.Set;

@Value
public class Student extends Person {
    String medicalTests;
    Set<Flight> flights;
    Set<TheoryClass> theoryClasses;
    Set<Course> courses;

    public Student(Long id,
                   String fName,
                   String sName,
                   String SSN,
                   Address address,
                   String medicalTests,
                   Set<Flight> flights,
                   Set<TheoryClass> theoryClasses,
                   Set<Course> courses) {
        super(id, fName, sName, SSN, address);
        this.medicalTests = medicalTests;
        this.flights = flights;
        this.theoryClasses = theoryClasses;
        this.courses = courses;
    }
}
