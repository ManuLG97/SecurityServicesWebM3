package securityservices.core.shared.services.serializers.xmlapis;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.InputSource;
import securityservices.shared.responses.ResultRequest;

public abstract class Jaxb {
    protected JAXBContext context;
    protected Marshaller javaToXml;
    protected Unmarshaller xmltojava;

    protected ResultRequest prepareUnmarshal(String xresponse) {
        try {
            xmltojava = context.createUnmarshaller();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xresponse));
            return ResultRequest.done(xmltojava.unmarshal(is));
        } catch (JAXBException ex) {
            return ResultRequest.fails("{\"Error\":\"" + ex.getMessage() + "\"}");
        }
    }

    protected ResultRequest<String> prepareMarshal(Object o) {
        StringWriter writer = new StringWriter();
        try {
            javaToXml = context.createMarshaller();
            javaToXml.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            javaToXml.marshal(o, writer);
            return ResultRequest.done(writer.getBuffer().toString()); 
        } catch (JAXBException ex) {
            return ResultRequest.fails("{\"Error\":\"" + ex.getMessage() + "\"}");
        }
    }  
}