package pl.polsl.ior.spring.persistance.course.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COURSES")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "students")
public class CourseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String certType;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String description;
    @ManyToMany(cascade = { CascadeType.ALL })
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "Course_Student",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private Set<StudentEntity> students = new HashSet<>();
}
