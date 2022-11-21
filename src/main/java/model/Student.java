package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENTS")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student extends Person implements Serializable {
    private String medicalTests;
    @OneToMany(mappedBy = "student")
    private Set<Flight> flights = new HashSet<>();
    @OneToMany(mappedBy = "student")
    private Set<TheoryClass> theoryClasses = new HashSet<>();
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name="STUDENT_COURSES",
            joinColumns=@JoinColumn(name="STUDENT_ID"),
            inverseJoinColumns=@JoinColumn(name="COURSE_ID"),
            foreignKey = @javax.persistence.ForeignKey(name="FK_STUDENT_COURSE"),
            inverseForeignKey = @javax.persistence.ForeignKey(name="FK_COURSE_STUDENT"))
    private Set<Course> courses = new HashSet<>();
}
