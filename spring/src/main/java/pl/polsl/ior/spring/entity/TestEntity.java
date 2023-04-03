package pl.polsl.ior.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TestEntity {
    @Id
    private Long id;

    private String value;
}
