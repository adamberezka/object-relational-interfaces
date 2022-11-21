package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "THEORY_CLASSES")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "student")
public class TheoryClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int hours;
    private int grade;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="STUDENT_ID", foreignKey = @javax.persistence.ForeignKey(name = "FK_THEORY_CLASS_STUDENT"))
    private Student student;
}
