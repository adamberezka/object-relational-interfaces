package model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERSONS")
@Inheritance(strategy=InheritanceType.JOINED)
@Data
@NoArgsConstructor
@ToString
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fName;
    private String sName;
    private String SSN;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="ADDRESS_ID", foreignKey = @javax.persistence.ForeignKey(name="FK_PERSON_ADDRESS"))
    private Address address;
}
