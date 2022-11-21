package goodies;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class BooleanToValidConverter implements AttributeConverter<Boolean, String>{

    private final static String VALID_STRING = "Yes";
    private final static String NOT_VALID_STRING = "No";

    @Override
    public String convertToDatabaseColumn(final Boolean attribute) {
        if (attribute == null)
            return "";
        else
            return attribute ? VALID_STRING : NOT_VALID_STRING;
    }

    @Override
    public Boolean convertToEntityAttribute(final String dbData) {
        if (VALID_STRING.equals(dbData))
            return true;
        else if (NOT_VALID_STRING.equals(dbData))
            return false;
        else return null;
    }
}
