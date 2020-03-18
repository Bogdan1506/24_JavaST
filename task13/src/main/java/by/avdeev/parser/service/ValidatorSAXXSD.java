package by.avdeev.parser.service;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class ValidatorSAXXSD {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameters is {}";

    public void validate(String fileName) throws ServiceException {
        logger.debug(START);
        logger.debug(PARAM, fileName);
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String schemaName = "E:\\24_JavaST\\task13\\target\\classes\\pizzeria.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
        } catch (SAXException e) {
            throw new ServiceException("validation " + fileName + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            throw new ServiceException(fileName + " is not valid because "
                    + e.getMessage());
        }
    }
}
