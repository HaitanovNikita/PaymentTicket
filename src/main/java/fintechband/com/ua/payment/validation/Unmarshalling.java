package fintechband.com.ua.payment.validation;

import fintechband.com.ua.payment.validation.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationException;
import java.io.StringReader;

@RequiredArgsConstructor
@Component
public class Unmarshalling {

    public <T extends Model> T validate(Class<T> modelClass, Unmarshaller unmarshaller, String xml) throws ValidationException {
        try {
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException exception) {
            throw new ValidationException("Message validation error: " + exception.getLinkedException().getMessage(), exception);
        }
    }
}
