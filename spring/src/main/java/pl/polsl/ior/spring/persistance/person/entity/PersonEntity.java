package pl.polsl.ior.spring.persistance.person.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import pl.polsl.ior.spring.persistance.address.entity.AddressEntity;

import java.io.Serializable;

@Entity
@Table(name = "PERSONS")
@Inheritance(strategy=InheritanceType.JOINED)
@Data
@NoArgsConstructor
@ToString
public class PersonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String surname;
    private String SSN;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name="ADDRESS_ID", foreignKey = @ForeignKey(name="FK_PERSON_ADDRESS"))
    private AddressEntity address;
}
