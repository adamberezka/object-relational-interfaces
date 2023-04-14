package pl.polsl.ior.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Person {
    private final Long id;
    private final String fName;
    private final String sName;
    private final String SSN;
    private final Address address;
}
