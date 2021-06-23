package securityservices.core.equipment.appservices;

import securityservices.core.component.client.appservices.*;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.equipment.domain.services.EquipmentDTO;
import securityservices.core.shared.services.serializers.Xml;
import securityservices.shared.responses.ResultRequest;

public class XmlEquipmentSerializer implements Serializer {

    private Xml xmlConverter;

    public XmlEquipmentSerializer(Xml xmlConvert) {
        this.xmlConverter = xmlConvert;
    }

    public ResultRequest<EquipmentDTO> unserialize(String data) {
        xmlConverter.set(data);
        try {
            EquipmentDTO equipment = new EquipmentDTO(
                    xmlConverter.getValueNode("code"),
                    xmlConverter.getValueNode("name"),
                    xmlConverter.getValueNode("type"),
                    xmlConverter.getValueNode("maker"),
                    xmlConverter.getValueNode("description"),
                    Double.valueOf(xmlConverter.getValueNode("price")),
                    Double.valueOf(xmlConverter.getValueNode("high")),
                    Double.valueOf(xmlConverter.getValueNode("wide")),
                    Double.valueOf(xmlConverter.getValueNode("deep")),
                    Double.valueOf(xmlConverter.getValueNode("weight")),
                    Boolean.valueOf(xmlConverter.getValueNode("fragile")),
                    xmlConverter.getValueNode("function"),
                    xmlConverter.getValueNode("components"),
                    Integer.valueOf(xmlConverter.getValueNode("power")),
                    xmlConverter.getValueNode("equipmentId")
            );
            return ResultRequest.done(equipment);
        } catch (Exception e) {
            return ResultRequest.fails("{\"Error\":\"EquipmentDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    public ResultRequest<String> serialize(Object e) {
        xmlConverter.createDocument();        
        xmlConverter.setRootNode("equipment");
        xmlConverter.setNode("code", ((EquipmentDTO) e).getCode());
        xmlConverter.setNode("name", ((EquipmentDTO) e).getName());
        xmlConverter.setNode("type", ((EquipmentDTO) e).getType());
        xmlConverter.setNode("maker", ((EquipmentDTO) e).getMaker());
        xmlConverter.setNode("description", ((EquipmentDTO) e).getDescription());
        xmlConverter.setNode("price", String.valueOf(((EquipmentDTO) e).getPrice()));
        xmlConverter.setNode("high", String.valueOf(((EquipmentDTO) e).getHigh()));
        xmlConverter.setNode("wide", String.valueOf(((EquipmentDTO) e).getWide()));
        xmlConverter.setNode("deep", String.valueOf(((EquipmentDTO) e).getDeep()));
        xmlConverter.setNode("weight", String.valueOf(((EquipmentDTO) e).getWeight()));
        xmlConverter.setNode("fragile", String.valueOf(((EquipmentDTO) e).isFragile()));
        xmlConverter.setNode("function", ((EquipmentDTO) e).getFunction());
        xmlConverter.setNode("components", ((EquipmentDTO) e).getComponents());
        xmlConverter.setNode("power", String.valueOf(((EquipmentDTO) e).getPower()));
        xmlConverter.setNode("equipmentId", ((EquipmentDTO) e).getEquipmentId());
        return ResultRequest.done(xmlConverter.toString());
    }
}
