package pl.polsl.ior.spring.api.conversion;

import pl.polsl.ior.spring.api.ApiTheoryClass;
import pl.polsl.ior.spring.domain.TheoryClass;

public abstract class TheoryClassApiConversion {

    public static ApiTheoryClass toApi(final TheoryClass theoryClass) {
        return new ApiTheoryClass(
                theoryClass.id(),
                theoryClass.name(),
                theoryClass.hours(),
                theoryClass.grade()
        );
    }

}
