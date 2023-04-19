package pl.polsl.ior.spring.persistance.flight.conversion;

import java.sql.Date;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public abstract class OffsetDateTimeConverter {

    public static Date toDate(final OffsetDateTime dateTime) {
        return Date.valueOf(dateTime.toLocalDate());
    }

    public static OffsetDateTime toOffsetDateTime(final Date date) {
        return OffsetDateTime.of(date.toLocalDate(), LocalTime.MIDNIGHT, ZoneOffset.UTC);
    }
}
