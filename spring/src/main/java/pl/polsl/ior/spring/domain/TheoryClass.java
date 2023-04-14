package pl.polsl.ior.spring.domain;

public record TheoryClass(
        Long id,
        String name,
        int hours,
        int grade,
        Student student
) {
}
