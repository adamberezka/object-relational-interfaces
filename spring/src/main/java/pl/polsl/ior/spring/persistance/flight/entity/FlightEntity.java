package pl.polsl.ior.spring.persistance.flight.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "FLIGHTS")
@Getter
@Setter
@NoArgsConstructor
public class FlightEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private int hours;
    private String description;
}
