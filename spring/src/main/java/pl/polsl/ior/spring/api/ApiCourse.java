package pl.polsl.ior.spring.api;

import lombok.Value;

import java.time.OffsetDateTime;
import java.util.Set;

@Value
public class ApiCourse {
    Long id;
    String certType;
    OffsetDateTime startDate;
    OffsetDateTime endDate;
    String description;
    Set<ApiStudent> students;
}
