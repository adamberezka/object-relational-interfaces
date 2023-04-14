package pl.polsl.ior.spring.persistance.flightinstructor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.polsl.ior.spring.persistance.person.entity.PersonEntity;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FLIGHT_INSTRUCTORS")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlightInstructorEntity extends PersonEntity implements Serializable {
    private long licenceNo;
    @Convert(converter = BooleanToValidConverter.class)
    private boolean valid;

    @OneToMany(mappedBy = "flightInstructor")
    private Set<FlightEntity> flights = new HashSet<>();
}