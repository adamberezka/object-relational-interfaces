package model;

import goodies.BooleanToValidConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FLIGHT_INSTRUCTORS")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlightInstructor extends Person implements Serializable {
    private long licenceNo;
    @Convert(converter = BooleanToValidConverter.class)
    private boolean valid;

    @OneToMany(mappedBy = "flightInstructor")
    private Set<Flight> flights = new HashSet<>();
}
