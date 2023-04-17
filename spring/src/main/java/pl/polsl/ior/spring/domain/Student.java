package pl.polsl.ior.spring.domain;

import lombok.Value;

import java.util.Set;

@Value
public final class Student extends Person {
    private final String medicalTests;
    private final Set<Flight> flights;
    private final Set<TheoryClass> theoryClasses;

    public Student(Long id,
                   String fName,
                   String sName,
                   String SSN,
                   Address address,
                   String medicalTests,
                   Set<Flight> flights,
                   Set<TheoryClass> theoryClasses) {
        super(id, fName, sName, SSN, address);
        this.medicalTests = medicalTests;
        this.flights = flights;
        this.theoryClasses = theoryClasses;
    }

    public Student withFlights(Set<Flight> flights) {
        return this.flights == flights ? this : new Student(
                this.getId(),
                this.getFName(),
                this.getSName(),
                this.getSSN(),
                this.getAddress(),
                this.medicalTests,
                flights,
                this.theoryClasses);
    }

    public Student withTheoryClasses(Set<TheoryClass> theoryClasses) {
        return this.theoryClasses == theoryClasses ? this : new Student(
                this.getId(),
                this.getFName(),
                this.getSName(),
                this.getSSN(),
                this.getAddress(),
                this.medicalTests,
                this.flights,
                theoryClasses
        );
    }
}
