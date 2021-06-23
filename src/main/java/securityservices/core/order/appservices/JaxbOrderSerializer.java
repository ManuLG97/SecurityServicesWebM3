package securityservices.core.order.appservices;

import securityservices.core.order.appservices.*;
import securityservices.core.shared.services.serializers.Serializer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import securityservices.core.order.domain.services.OrderDTO;
import securityservices.core.order.appservices.JaxbOrderDTO;
import securityservices.core.shared.services.serializers.xmlapis.Jaxb;
import securityservices.shared.responses.ResultRequest;

public class JaxbOrderSerializer extends Jaxb implements Serializer {

    private JaxbOrderSerializer() {
    }

    //apliquem sistemàticament el mateix concepte de tractament d'errors
    public static ResultRequest<JaxbOrderSerializer> getInstance() {
        try {
            JaxbOrderSerializer jaxbOrder = new JaxbOrderSerializer();
            //excepcio que genera la clase, i de la que volem fugir, mantenint el nostre tractament d'errors
            jaxbOrder.context = JAXBContext.newInstance(JaxbOrderDTO.class);
            return ResultRequest.done(jaxbOrder);
        } catch (JAXBException ex) {
            return ResultRequest.fails("{\"Error\":\"JAXBContext fails\",\"Details\":\"" + ex.toString() + "\"}");
        }
    }

    public ResultRequest<OrderDTO> unserialize(String xresponse) {
        if (super.prepareUnmarshal(xresponse).failed()) {
            return ResultRequest.fails("{\"Error\":\"JAXB unmarshal fails\","
                    + "\"Details\":\"Can't unserialize xmldata to OrderDTO. \""
                    + super.prepareUnmarshal(xresponse).getError()
                    + "}");
        } else {

            JaxbOrderDTO jaxbodto = (JaxbOrderDTO) super.prepareUnmarshal(xresponse).getValue();
            OrderDTO odto = new OrderDTO(jaxbodto.getCode(),
                    jaxbodto.getCreator(),
                    jaxbodto.getValue(),
                    jaxbodto.getSurcharges(),
                    jaxbodto.getType(),
                    jaxbodto.getStatus(),
                    jaxbodto.getAdditonalInfo(),
                    jaxbodto.getBeginDate(),
                    jaxbodto.getFinishDate(),
                    jaxbodto.getPaymentType(),
                    jaxbodto.getPaymentDate(),
                    jaxbodto.getOrderId());
            return ResultRequest.done(odto);
        }
    }

    public ResultRequest<String> serialize(Object orderDto) {
        JaxbOrderDTO jaxbodto = new JaxbOrderDTO(((OrderDTO) orderDto).getCode(),
                ((OrderDTO) orderDto).getCreator(),
                ((OrderDTO) orderDto).getValue(),
                ((OrderDTO) orderDto).getSurcharges(),
                ((OrderDTO) orderDto).getType(),
                ((OrderDTO) orderDto).getStatus(),
                ((OrderDTO) orderDto).getAdditonalInfo(),
                ((OrderDTO) orderDto).getBeginDate(),
                ((OrderDTO) orderDto).getFinishDate(),
                ((OrderDTO) orderDto).getPaymentType(),
                ((OrderDTO) orderDto).getPaymentDate(),
                ((OrderDTO) orderDto).getOrderId());

        if (super.prepareMarshal(jaxbodto).failed()) {
            return ResultRequest.fails("{\"Error\":\"JAXB marshal fails\","
                    + "\"Details\":\"Can't serialize OrderDTO to xmldata. \""
                    + super.prepareMarshal(jaxbodto).getError()
                    + "}");
        } else {
            String xmlOrder = super.prepareMarshal(jaxbodto).getValue();
            return ResultRequest.done(xmlOrder);
        }
    }

}
