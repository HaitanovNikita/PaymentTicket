package fintechband.com.ua.payment.config;

import fintechband.com.ua.payment.validation.model.application.ApplicationRequest;
import fintechband.com.ua.payment.validation.model.application.ApplicationResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author nhaitanov
 */
@Configuration
public class Config {

    @Value("classpath:xsd.application/REQUEST_APPLICATION.xsd")
    Resource xsdFileForApplicationRequest;
    @Value("classpath:xsd.application/RESPONSE_APPLICATION.xsd")
    Resource xsdFileForApplicationResponse;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @SneakyThrows(IOException.class)
    File xsdFileForApplicationRequest() {
        return xsdFileForApplicationRequest.getFile();
    }

    @Bean
    @SneakyThrows(IOException.class)
    File xsdFileForApplicationResponse() {
        return xsdFileForApplicationResponse.getFile();
    }

    @Bean
    SchemaFactory schemaFactory() {
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    }

    @Bean
    Schema schemaForApplicationRequest(@Qualifier("xsdFileForApplicationRequest") File file) throws SAXException {
        return schemaFactory().newSchema(file);
    }

    @Bean
    Schema schemaForApplicationResponse(@Qualifier("xsdFileForApplicationResponse") File file) throws SAXException {
        return schemaFactory().newSchema(file);
    }

    @Bean
    JAXBContext jaxbContextForApplicationRequest() throws JAXBException {
        return JAXBContext.newInstance(ApplicationRequest.class);
    }

    @Bean
    JAXBContext jaxbContextForApplicationResponse() throws JAXBException {
        return JAXBContext.newInstance(ApplicationResponse.class);
    }

    @Bean
    Marshaller marshallingForApplicationResponse(@Qualifier("jaxbContextForApplicationResponse") JAXBContext jaxbContextForAccountResponse,
                                                 @Qualifier("schemaForApplicationResponse") Schema schemaForAccountResponse)
            throws JAXBException {
        Marshaller marshaller = jaxbContextForAccountResponse.createMarshaller();
        marshaller.setSchema(schemaForAccountResponse);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    @Bean
    Unmarshaller unmarshallingForApplicationRequest(@Qualifier("jaxbContextForApplicationRequest") JAXBContext jaxbContextForAccountRequest,
                                                    @Qualifier("schemaForApplicationRequest") Schema schemaForApplicationRequest)
            throws JAXBException {
        Unmarshaller unmarshaller = jaxbContextForApplicationRequest().createUnmarshaller();
        unmarshaller.setSchema(schemaForApplicationRequest);
        return unmarshaller;
    }

    @Bean
    Unmarshaller unmarshallingForApplicationResponse(@Qualifier("jaxbContextForApplicationResponse") JAXBContext jaxbContextForAccountResponse,
                                                    @Qualifier("schemaForApplicationResponse") Schema schemaForApplicationResponse)
            throws JAXBException {
        Unmarshaller unmarshaller = jaxbContextForApplicationResponse().createUnmarshaller();
        unmarshaller.setSchema(schemaForApplicationResponse);
        return unmarshaller;
    }
}
