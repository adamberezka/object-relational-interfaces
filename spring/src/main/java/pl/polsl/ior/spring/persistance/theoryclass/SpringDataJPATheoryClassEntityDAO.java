package pl.polsl.ior.spring.persistance.theoryclass;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.ior.spring.persistance.theoryclass.entity.TheoryClassEntity;

public interface SpringDataJPATheoryClassEntityDAO extends JpaRepository<TheoryClassEntity, Long> {
}
