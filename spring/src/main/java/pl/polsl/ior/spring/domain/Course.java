package pl.polsl.ior.spring.domain;

import java.time.OffsetDateTime;
import java.util.Set;

public record Course(
        Long id,
        String certType,
        OffsetDateTime startDate,
        OffsetDateTime endDate,
        String description,
        Set<Student> students
) {
}
