package securityservices.core.component.client.appservices;

import securityservices.core.shared.services.serializers.Serializer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import securityservices.core.component.client.domain.services.ClientDTO;
import securityservices.core.shared.services.serializers.xmlapis.Jaxb;
import securityservices.shared.responses.ResultRequest;

public class JaxbClientSerializer extends Jaxb implements Serializer {

    private JaxbClientSerializer() {
    }

    //apliquem sistemàticament el mateix concepte de tractament d'errors
    public static ResultRequest<JaxbClientSerializer> getInstance() {
        try {
            JaxbClientSerializer jaxbClient = new JaxbClientSerializer();
            //excepcio que genera la clase, i de la que volem fugir, mantenint el nostre tractament d'errors
            jaxbClient.context = JAXBContext.newInstance(JaxbClientDTO.class);
            return ResultRequest.done(jaxbClient);
        } catch (JAXBException ex) {
            return ResultRequest.fails("{\"Error\":\"JAXBContext fails\",\"Details\":\"" + ex.toString() + "\"}");
        }
    }

    public ResultRequest<ClientDTO> unserialize(String xresponse) {
        if (super.prepareUnmarshal(xresponse).failed()) {
            return ResultRequest.fails("{\"Error\":\"JAXB unmarshal fails\","
                    + "\"Details\":\"Can't unserialize xmldata to ClientDTO. \""
                    + super.prepareUnmarshal(xresponse).getError()
                    + "}");
        } else {
            JaxbClientDTO jaxbcdto = (JaxbClientDTO) super.prepareUnmarshal(xresponse).getValue();
            ClientDTO cdto = new ClientDTO(jaxbcdto.getName(),
                    jaxbcdto.getCode(),
                    jaxbcdto.getAddress(),
                    jaxbcdto.getPhone(),
                    jaxbcdto.getEmail(),
                    jaxbcdto.isCompany(),
                    jaxbcdto.getBirthday(),
                    jaxbcdto.getClientId(),
                    jaxbcdto.getNumEquipments(),
                    jaxbcdto.getPassword());
            return ResultRequest.done(cdto);
        }
    }

    public ResultRequest<String> serialize(Object clientDto) {
        JaxbClientDTO jaxbcdto = new JaxbClientDTO(((ClientDTO) clientDto).getName(),
                ((ClientDTO) clientDto).getCode(),
                ((ClientDTO) clientDto).getAddress(),
                ((ClientDTO) clientDto).getPhone(),
                ((ClientDTO) clientDto).getEmail(),
                ((ClientDTO) clientDto).isCompany(),
                ((ClientDTO) clientDto).getBirthday(),
                ((ClientDTO) clientDto).getClientId(),
                ((ClientDTO) clientDto).getNumEquipments(),
                ((ClientDTO) clientDto).getPassword());

        if (super.prepareMarshal(jaxbcdto).failed()) {
            return ResultRequest.fails("{\"Error\":\"JAXB marshal fails\","
                    + "\"Details\":\"Can't serialize ClientDTO to xmldata. \"" 
                    + super.prepareMarshal(jaxbcdto).getError()
                    + "}");
        } else {
            String xmlClient = super.prepareMarshal(jaxbcdto).getValue();
            return ResultRequest.done(xmlClient);
        }
    }

}
