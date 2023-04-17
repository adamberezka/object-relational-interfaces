package pl.polsl.ior.spring.domain;

public record Address(
        Long id,
        String country,
        String city,
        String postalCode,
        String street
) {

}
