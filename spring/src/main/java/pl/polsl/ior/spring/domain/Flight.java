package pl.polsl.ior.spring.domain;

import java.time.OffsetDateTime;


public record Flight(
        Long id,
        OffsetDateTime date,
        int hours,
        String description,
        FlightInstructor flightInstructor,
        Student student
) {
}
