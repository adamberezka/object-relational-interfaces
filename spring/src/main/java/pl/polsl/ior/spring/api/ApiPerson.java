package pl.polsl.ior.spring.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiPerson {
    private Long id;
    private String fName;
    private String sName;
    private String SSN;
    private ApiAddress address;
}
