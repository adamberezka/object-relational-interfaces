package pl.polsl.ior.spring.persistance.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import pl.polsl.ior.spring.persistance.theoryclass.entity.TheoryClassEntity;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;
import pl.polsl.ior.spring.persistance.person.entity.PersonEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENTS")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentEntity extends PersonEntity implements Serializable {
    private String medicalTests;
    @OneToMany(mappedBy = "student")
    private Set<FlightEntity> flights = new HashSet<>();
    @OneToMany(mappedBy = "student")
    private Set<TheoryClassEntity> theoryClasses = new HashSet<>();
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name="STUDENT_COURSES",
            joinColumns=@JoinColumn(name="STUDENT_ID"),
            inverseJoinColumns=@JoinColumn(name="COURSE_ID"),
            foreignKey = @ForeignKey(name="FK_STUDENT_COURSE"),
            inverseForeignKey = @ForeignKey(name="FK_COURSE_STUDENT"))
    private Set<CourseEntity> courses = new HashSet<>();
}
