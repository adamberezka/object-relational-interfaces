package pl.polsl.ior.spring.api.conversion;

import pl.polsl.ior.spring.api.ApiAddress;
import pl.polsl.ior.spring.domain.Address;

public abstract class AddressApiConversion {

    public static ApiAddress toApi(final Address address) {
        return new ApiAddress(
                address.id(),
                address.country(),
                address.city(),
                address.postalCode(),
                address.street()
        );
    }
}
