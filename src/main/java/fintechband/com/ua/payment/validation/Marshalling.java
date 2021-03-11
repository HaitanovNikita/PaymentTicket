package fintechband.com.ua.payment.validation;

import fintechband.com.ua.payment.validation.model.Model;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.ValidationException;
import java.io.StringWriter;
@Component
public class Marshalling {

    public <T extends Model> String marshalling(T responseObject, Marshaller marshaller) throws ValidationException {
        try {
            StringWriter sw = new StringWriter();
            marshaller.marshal(responseObject, sw);
            return sw.toString();
        } catch (JAXBException exception) {
            throw new ValidationException("Message validation error: " + exception.getLinkedException().getMessage(), exception);
        }
    }
}
