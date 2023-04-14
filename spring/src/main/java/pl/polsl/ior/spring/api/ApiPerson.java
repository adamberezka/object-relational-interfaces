package pl.polsl.ior.spring.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiPerson {
    private final Long id;
    private final String fName;
    private final String sName;
    private final String SSN;
    private final ApiAddress address;
}
