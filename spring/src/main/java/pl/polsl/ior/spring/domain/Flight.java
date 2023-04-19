package pl.polsl.ior.spring.domain;

import lombok.Value;
import lombok.With;

import java.time.OffsetDateTime;

@Value
@With
public class Flight {
    Long id;
    OffsetDateTime date;
    int hours;
    String description;
}
