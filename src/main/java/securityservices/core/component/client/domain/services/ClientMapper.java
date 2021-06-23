package securityservices.core.component.client.domain.services;


import securityservices.core.component.client.domain.model.Client;
import securityservices.shared.responses.ResultRequest;

public class ClientMapper {

    public static ResultRequest<Client> componentFromDTO(ClientDTO cdto) {
        return Client.getInstance(
                cdto.getName(),
                cdto.getCode(),
                cdto.getAddress(),
                cdto.getPhone(),
                cdto.getEmail(),
                cdto.isCompany(),
                cdto.getBirthday(),
                cdto.getNumEquipments(),
                cdto.getPassword()
        );
    }

    public static ClientDTO dtoFromComponent(Client c) {
        return new ClientDTO(
                c.getName(),
                c.getCode(),
                c.getAddress(),
                c.getPhone(),
                c.getEmail(),
                c.isCompany(),
                c.getBirthday(),
                c.getClientId(),
                c.getNumEquipments(),
                c.getPassword()
        );
    }
}