package pl.polsl.ior.spring.repository;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;
import pl.polsl.ior.spring.entity.TestEntity;

@Component
public class TestRepository implements Repository<TestEntity, Long> {
}
