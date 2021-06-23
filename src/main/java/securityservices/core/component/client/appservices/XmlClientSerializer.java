package securityservices.core.component.client.appservices;

import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.component.client.domain.services.ClientDTO;
import securityservices.core.shared.services.serializers.Xml;
import securityservices.shared.responses.ResultRequest;

public class XmlClientSerializer implements Serializer {

    private Xml xmlConverter;

    public XmlClientSerializer(Xml xmlConvert) {
        this.xmlConverter = xmlConvert;
    }

    public ResultRequest<ClientDTO> unserialize(String data) {
        xmlConverter.set(data);
        try {
            ClientDTO client = new ClientDTO(
                    xmlConverter.getValueNode("name"),
                    xmlConverter.getValueNode("code"),
                    xmlConverter.getValueNode("address"),
                    xmlConverter.getValueNode("phone"),
                    xmlConverter.getValueNode("email"),
                    Boolean.valueOf(xmlConverter.getValueNode("iscompany")),
                    xmlConverter.getValueNode("birthday"),
                    xmlConverter.getValueNode("clientid"),
                    Integer.valueOf(xmlConverter.getValueNode("equipments")),
                    xmlConverter.getValueNode("password")
            );
            return ResultRequest.done(client);
        } catch (Exception e) {
            return ResultRequest.fails("{\"Error\":\"ClientDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    public ResultRequest<String> serialize(Object c) {
        xmlConverter.createDocument();
        xmlConverter.setRootNode("client");
        xmlConverter.setNode("code", ((ClientDTO) c).getCode());
        xmlConverter.setNode("name", ((ClientDTO) c).getName());
        xmlConverter.setNode("address", ((ClientDTO) c).getAddress());
        xmlConverter.setNode("phone", ((ClientDTO) c).getPhone());
        xmlConverter.setNode("email", ((ClientDTO) c).getEmail());
        xmlConverter.setNode("iscompany", String.valueOf(((ClientDTO) c).isCompany()));
        xmlConverter.setNode("birthday", ((ClientDTO) c).getBirthday());
        xmlConverter.setNode("clientid", ((ClientDTO) c).getClientId());
        xmlConverter.setNode("equipments", String.valueOf(((ClientDTO) c).getNumEquipments()));
        xmlConverter.setNode("password", ((ClientDTO) c).getPassword());
        return ResultRequest.done(xmlConverter.toString());
    }
}
