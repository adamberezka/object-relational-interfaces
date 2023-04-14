package pl.polsl.ior.spring.api;

import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class ApiFlight {
    Long id;
    OffsetDateTime date;
    int hours;
    String description;
    ApiFlightInstructor flightInstructor;
    ApiStudent student;
}
