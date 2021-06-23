package securityservices.core.service.appservices;

import securityservices.core.service.appservices.*;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.service.domain.services.ServiceDTO;
import securityservices.core.shared.services.serializers.Xml;
import securityservices.shared.responses.ResultRequest;

public class XmlServiceSerializer implements Serializer {

    private Xml xmlConverter;

    public XmlServiceSerializer(Xml xmlConvert) {
        this.xmlConverter = xmlConvert;
    }

    public ResultRequest<ServiceDTO> unserialize(String data) {
        xmlConverter.set(data);
        try {
            ServiceDTO service = new ServiceDTO(
                    xmlConverter.getValueNode("name"),
                    xmlConverter.getValueNode("code"),
                    xmlConverter.getValueNode("type"),
                    xmlConverter.getValueNode("description"),
                    xmlConverter.getValueNode("maker"),
                    Double.valueOf(xmlConverter.getValueNode("price")),
                    xmlConverter.getValueNode("periodicity"),
                    xmlConverter.getValueNode("conditions"),
                    xmlConverter.getValueNode("startDate"),
                    xmlConverter.getValueNode("finishDate"),
                    xmlConverter.getValueNode("serviceId")
            );
            return ResultRequest.done(service);
        } catch (Exception e) {
            return ResultRequest.fails("{\"Error\":\"ServiceDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    public ResultRequest<String> serialize(Object s) {
        xmlConverter.createDocument();
        xmlConverter.setRootNode("service");
        xmlConverter.setNode("name", ((ServiceDTO) s).getName());
        xmlConverter.setNode("code", ((ServiceDTO) s).getCode());
        xmlConverter.setNode("type", ((ServiceDTO) s).getType());
        xmlConverter.setNode("description", ((ServiceDTO) s).getDescription());
        xmlConverter.setNode("maker", ((ServiceDTO) s).getMaker());
        xmlConverter.setNode("price", String.valueOf(((ServiceDTO) s).getPrice()));
        xmlConverter.setNode("periodicity", ((ServiceDTO) s).getPeriodicity());
        xmlConverter.setNode("conditions", ((ServiceDTO) s).getConditions());
        xmlConverter.setNode("startDate", ((ServiceDTO) s).getBeginDate());
        xmlConverter.setNode("finishDate", ((ServiceDTO) s).getFinishDate());
        xmlConverter.setNode("serviceId", ((ServiceDTO) s).getServiceId());
        return ResultRequest.done(xmlConverter.toString());
    }
}
