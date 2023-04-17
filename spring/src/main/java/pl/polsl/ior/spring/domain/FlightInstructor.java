package pl.polsl.ior.spring.domain;

import lombok.Value;

import java.util.Set;

@Value
public class FlightInstructor extends Person {
    long licenceNo;
    boolean valid;

    public FlightInstructor(Long id,
                            String fName,
                            String sName,
                            String SSN,
                            Address address,
                            long licenceNo,
                            boolean valid) {
        super(id, fName, sName, SSN, address);
        this.licenceNo = licenceNo;
        this.valid = valid;
    }
}
