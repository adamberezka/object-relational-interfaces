package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String certType;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String description;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
