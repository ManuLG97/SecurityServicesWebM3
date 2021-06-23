package securityservices.core.component.client.domain.model;

import java.util.UUID;
import securityservices.shared.responses.ResultResponses;
import securityservices.shared.responses.ResultRequest;
import securityservices.core.shared.stakeholders.Person;
import securityservices.core.shared.stakeholders.StakeHolder;

public class Client extends Person implements StakeHolder {

    protected UUID clientId;
    protected String password;
    protected int numEquipments;

    protected Client() {
        this.setClientId();
    }

    public static Client getInstance() {
        return new Client();
    }

    public static ResultRequest<Client> getInstance(String name, String code, String address, String phone,
            String mail, Boolean company, String birthday, int numequipments, String password) {

        Client client = new Client();

        ResultRequest result = client.setName(name);
        if (result.failed()) {
            return result;
        }

        result = client.setCode(code);
        if (result.failed()) {
            return result;
        }

        client.setAddress(address);

        result = client.setPhone(phone);
        if (result.failed()) {
            return result;
        }

        result = client.setEmail(mail);
        if (result.failed()) {
            return result;
        }

        result = client.setBirthday(birthday);
        if (result.failed()) {
            return result;
        }

        result = client.setNumEquipments(numequipments);
        if (result.failed()) {
            return result;
        }

        result = client.setPassword(password);
        if (result.failed()) {
            return result;
        }

        client.setCompany(company);

        return ResultRequest.done(client);
    }

    public String getClientId() {
        return clientId.toString();
    }

    protected void setClientId() {
        this.clientId = UUID.randomUUID();
    }

    public String getPassword() {
        return password;
    }

    public ResultRequest setPassword(String password) {
        if (password == null || password.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid password\"");
        }
        this.password = password;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public int getNumEquipments() {
        return numEquipments;
    }

    public ResultRequest setNumEquipments(int numEquipments) {
        if (numEquipments <= 0) {
            return ResultRequest.fails("\"Error\":\"invalid number of equipments\"");
        }
        this.numEquipments = numEquipments;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getContactData() {
        return "email:" + this.getEmail() + ";" + "phone:" + this.getPhone();
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
