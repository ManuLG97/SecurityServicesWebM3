package securityservices.core.component.client.appservices;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/*
    DTO generado para adaptarnos a la magia de JAXB
 */
@XmlRootElement(name = "client")
public class JaxbClientDTO {

    private String name, code, address, phone, email, birthday, clientId, password;
    private Boolean company;
    private int numEquipments;
    
    public JaxbClientDTO(){}
    
    public JaxbClientDTO(String name, String code, String address, String phone, String email, boolean company, 
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

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "dni")
    public String getCode() {
        return code;
    }

    @XmlElement(name = "address")
    public String getAddress() {
        return address;
    }

    @XmlElement(name = "phone")
    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    @XmlElement(name = "iscompany")
    public Boolean isCompany() {
        return company;
    }
    
    @XmlElement(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    @XmlElement(name = "clientID")
    public String getClientId() {
        return clientId;
    }

    @XmlElement(name = "numequipments")
    public int getNumEquipments() {
        return numEquipments;
    }

    //@XmlTransient
    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompany(Boolean company) {
        this.company = company;
    }

    public void setNumEquipments(int numEquipments) {
        this.numEquipments = numEquipments;
    }
    
    
}
