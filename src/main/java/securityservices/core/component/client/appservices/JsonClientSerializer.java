package securityservices.core.component.client.appservices;


import securityservices.shared.responses.ResultRequest;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.component.client.domain.services.ClientDTO;

public class JsonClientSerializer implements Serializer {

    private Json jClient = JsonObjectFactory.getInstance();

    public JsonClientSerializer() {
    }

    @Override
    public ResultRequest<ClientDTO> unserialize(String data) {
        jClient.set(data);
        try {
            ClientDTO client = new ClientDTO(
                    jClient.get("name"),
                    jClient.get("code"),
                    jClient.get("address"),
                    jClient.get("phone"),
                    jClient.get("email"),
                    Boolean.valueOf(jClient.get("iscompany")),
                    jClient.get("birthday"),
                    jClient.get("clientid"),
                    Integer.valueOf(jClient.get("numequipments")),
                    jClient.get("password")
            );
            return ResultRequest.done(client);

        } catch (Exception e) {
            return ResultRequest.fails("{\"Error\":\"ClientDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public ResultRequest<String> serialize(Object client) {
        jClient.removeAll();
        jClient.set("code", ((ClientDTO) client).getCode());
        jClient.set("name", ((ClientDTO) client).getName());
        jClient.set("clientid", ((ClientDTO) client).getClientId());
        jClient.set("numequipments", String.valueOf(((ClientDTO) client).getNumEquipments()));
        jClient.set("email", ((ClientDTO) client).getEmail());
        jClient.set("iscompany", String.valueOf(((ClientDTO) client).isCompany()));
        jClient.set("phone", ((ClientDTO) client).getPhone());
        jClient.set("address", ((ClientDTO) client).getAddress());
        jClient.set("birthday", ((ClientDTO) client).getBirthday());

        return ResultRequest.done(jClient.toString());
    }

}
