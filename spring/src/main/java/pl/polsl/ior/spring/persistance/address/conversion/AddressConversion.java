package pl.polsl.ior.spring.persistance.address.conversion;

import pl.polsl.ior.spring.domain.Address;
import pl.polsl.ior.spring.persistance.address.entity.AddressEntity;
import pl.polsl.ior.spring.persistance.person.conversion.PersonConversion;

public abstract class AddressConversion {

    public static AddressEntity toEntity(final Address address) {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(address.id());
        addressEntity.setCountry(address.country());
        addressEntity.setCity(address.city());
        addressEntity.setPostalCode(address.postalCode());
        addressEntity.setStreet(address.street());
        return addressEntity;
    }

    public static Address toDomain(final AddressEntity addressEntity) {
        return new Address(
                addressEntity.getId(),
                addressEntity.getCountry(),
                addressEntity.getCity(),
                addressEntity.getPostalCode(),
                addressEntity.getStreet()
        );
    }
}
