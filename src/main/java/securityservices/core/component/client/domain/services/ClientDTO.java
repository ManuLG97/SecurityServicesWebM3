package securityservices.core.component.client.domain.services;

/*import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;*/
/*
DTO inmutable generado a traves del constructor para transferirlo a otra capa
sin permitir cambios en su contenido
 */
public class ClientDTO {

    private final String name, code, address, phone, email, birthday, clientId, password;
    private final Boolean company;
    private final int numEquipments;

    public ClientDTO(String name, String code, String address, String phone, String email, Boolean company, 
            String birthday, String clientId, int numEquipments, String password) {
        this.name = name;
        this.code = code;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.birthday = birthday;
        this.clientId = clientId;
        this.numEquipments = numEquipments;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isCompany() {
        return company;
    }
    
    public String getBirthday() {
        return birthday;
    }

    public String getClientId() {
        return clientId;
    }

    public int getNumEquipments() {
        return numEquipments;
    }

    public String getPassword() {
        return password;
    }
}
