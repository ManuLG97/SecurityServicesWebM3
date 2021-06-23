package securityservices.core.equipment.appservices;

import securityservices.shared.responses.ResultRequest;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.equipment.domain.services.EquipmentDTO;

public class JsonEquipmentSerializer implements Serializer {

    private Json jEquipment = JsonObjectFactory.getInstance();

    public JsonEquipmentSerializer() {
    }

    @Override
    public ResultRequest<EquipmentDTO> unserialize(String data) {
        jEquipment.set(data);
        try {
            EquipmentDTO equipment = new EquipmentDTO(
                    jEquipment.get("code"),
                    jEquipment.get("name"),
                    jEquipment.get("type"),
                    jEquipment.get("maker"),
                    jEquipment.get("description"),
                    Double.valueOf(jEquipment.get("price")),
                    Double.valueOf(jEquipment.get("high")),
                    Double.valueOf(jEquipment.get("wide")),
                    Double.valueOf(jEquipment.get("deep")),
                    Double.valueOf(jEquipment.get("weight")),
                    Boolean.valueOf(jEquipment.get("fragile")),
                    jEquipment.get("function"),
                    jEquipment.get("components"),
                    Integer.valueOf(jEquipment.get("power")),
                    jEquipment.get("equipmentid")
            );
            return ResultRequest.done(equipment);

        } catch (Exception e) {
            return ResultRequest.fails("{\"Error\":\"EquipmentDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public ResultRequest<String> serialize(Object equipment) {
        jEquipment.removeAll();
        jEquipment.set("code", ((EquipmentDTO) equipment).getCode());
        jEquipment.set("name", ((EquipmentDTO) equipment).getName());
        jEquipment.set("type", ((EquipmentDTO) equipment).getType());
        jEquipment.set("maker", ((EquipmentDTO) equipment).getMaker());
        jEquipment.set("description", ((EquipmentDTO) equipment).getDescription());
        jEquipment.set("price", String.valueOf(((EquipmentDTO) equipment).getPrice()));
        jEquipment.set("high", String.valueOf(((EquipmentDTO) equipment).getHigh()));
        jEquipment.set("wide", String.valueOf(((EquipmentDTO) equipment).getWide()));
        jEquipment.set("deep", String.valueOf(((EquipmentDTO) equipment).getDeep()));
        jEquipment.set("weight", String.valueOf(((EquipmentDTO) equipment).getWeight()));
        jEquipment.set("fragile", String.valueOf(((EquipmentDTO) equipment).isFragile()));
        jEquipment.set("function", ((EquipmentDTO) equipment).getFunction());
        jEquipment.set("components", ((EquipmentDTO) equipment).getComponents());
        jEquipment.set("power", String.valueOf(((EquipmentDTO) equipment).getPower()));
        jEquipment.set("equipmentid", ((EquipmentDTO) equipment).getEquipmentId());

        return ResultRequest.done(jEquipment.toString());
    }

}
