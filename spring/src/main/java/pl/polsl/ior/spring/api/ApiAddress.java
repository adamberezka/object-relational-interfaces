package pl.polsl.ior.spring.api;

import lombok.Value;

@Value
public class ApiAddress {
        Long id;
        String country;
        String city;
        String postalCode;
        String street;
        ApiPerson person;
}
