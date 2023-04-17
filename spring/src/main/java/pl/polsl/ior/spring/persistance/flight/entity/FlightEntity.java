package pl.polsl.ior.spring.persistance.flight.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import pl.polsl.ior.spring.persistance.flightinstructor.entity.FlightInstructorEntity;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "FLIGHTS")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"student", "flightInstructor"})
public class FlightEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime date;
    private int hours;
    private String description;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="FLIGHT_INSTRUCTOR_ID", foreignKey = @ForeignKey(name="FK_FLIGHT_FLIGHT_INSTRUCTOR"))
    private FlightInstructorEntity flightInstructor;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="STUDENT_ID", foreignKey = @ForeignKey(name="FK_FLIGHT_STUDENT"))
    private StudentEntity student;
}
