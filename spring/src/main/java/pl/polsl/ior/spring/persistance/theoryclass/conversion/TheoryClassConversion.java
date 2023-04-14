package pl.polsl.ior.spring.persistance.theoryclass.conversion;

import pl.polsl.ior.spring.domain.TheoryClass;
import pl.polsl.ior.spring.persistance.student.conversion.StudentConversion;
import pl.polsl.ior.spring.persistance.theoryclass.entity.TheoryClassEntity;

public abstract class TheoryClassConversion {

    public static TheoryClassEntity toEntity(final TheoryClass theoryClass) {
        final TheoryClassEntity theoryClassEntity = new TheoryClassEntity();
        theoryClassEntity.setId(theoryClass.id());
        theoryClassEntity.setName(theoryClass.name());
        theoryClassEntity.setHours(theoryClass.hours());
        theoryClassEntity.setGrade(theoryClass.grade());
        theoryClassEntity.setStudent(StudentConversion.toEntity(theoryClass.student()));
        return theoryClassEntity;
    }

    public static TheoryClass toDomain(final TheoryClassEntity theoryClassEntity) {
        return new TheoryClass(
                theoryClassEntity.getId(),
                theoryClassEntity.getName(),
                theoryClassEntity.getHours(),
                theoryClassEntity.getGrade(),
                StudentConversion.toDomain(theoryClassEntity.getStudent())
        );
    }
}
