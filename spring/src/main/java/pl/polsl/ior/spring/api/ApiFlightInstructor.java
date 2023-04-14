package pl.polsl.ior.spring.api;

import lombok.Value;

import java.util.Set;

@Value
public class ApiFlightInstructor extends ApiPerson {
    long licenceNo;
    boolean valid;
    Set<ApiFlight> flights;

    public ApiFlightInstructor(Long id,
                               String fName,
                               String sName,
                               String SSN,
                               ApiAddress address,
                               long licenceNo,
                               boolean valid,
                               Set<ApiFlight> flights) {
        super(id, fName, sName, SSN, address);
        this.licenceNo = licenceNo;
        this.valid = valid;
        this.flights = flights;
    }
}
