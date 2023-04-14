package pl.polsl.ior.spring.persistance.address.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.polsl.ior.spring.persistance.person.entity.PersonEntity;

import java.io.Serializable;

@Entity
@Table(name = "ADDRESSES")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "person")
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    @OneToOne(mappedBy = "address")
    private PersonEntity person;
}
