package by.avdeev.parser.service;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidatorSAXXSD {
    public void validate(String fileName) throws ServiceException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String schemaName = "E:\\24_JavaST\\task13\\target\\classes\\pizzeria.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            System.out.println(fileName + " is valid.");
        } catch (SAXException e) {
            throw new ServiceException("validation " + fileName + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            throw new ServiceException(fileName + " is not valid because "
                    + e.getMessage());
        }
    }
}
