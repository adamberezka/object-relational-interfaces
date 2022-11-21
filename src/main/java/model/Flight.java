package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "FLIGHTS")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"student", "flightInstructor"})
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OffsetDateTime date;
    private int hours;
    private String description;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="FLIGHT_INSTRUCTOR_ID", foreignKey = @javax.persistence.ForeignKey(name="FK_FLIGHT_FLIGHT_INSTRUCTOR"))
    private FlightInstructor flightInstructor;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="STUDENT_ID", foreignKey = @javax.persistence.ForeignKey(name="FK_FLIGHT_STUDENT"))
    private Student student;

    public Flight(OffsetDateTime date, int hours, String description) {
        this.date = date;
        this.hours = hours;
        this.description = description;
    }
}
