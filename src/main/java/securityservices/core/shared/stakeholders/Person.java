package securityservices.core.shared.stakeholders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import securityservices.shared.responses.ResultResponses;
import securityservices.shared.responses.ResultRequest;

public abstract class Person {

    protected String name, code, address, phone, email;
    protected Boolean company;
    protected LocalDate birthday;
    protected DateTimeFormatter personDateFormat = DateTimeFormatter.ofPattern("dd'-'MMMM'-'yyyy");

    protected Person() {
    }

    public String getName() {
        return name;
    }

    public ResultRequest setName(String name) {
        if (name == null || name.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid name\"");
        }
        this.name = name;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getCode() {
        return code;
    }

    public ResultRequest setCode(String code) {
        if (code == null || code.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid id person value\"");
        }
        this.code = code;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public ResultRequest setPhone(String phone) {
        if (phone == null || phone.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid phone\"");
        }
        this.phone = phone;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getEmail() {
        return email;
    }

    public ResultRequest setEmail(String email) {
        if (email == null || email.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid email\"");
        }
        this.email = email;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getBirthday() {
        if (birthday == null) {
            return "";
        }
        return birthday.format(personDateFormat);
    }

    public ResultRequest setBirthday(String birthday) {
        try {
            this.birthday = LocalDate.parse(birthday, personDateFormat);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid birthday: " + e.getMessage() + "\"");
        }
    }

    public Boolean isCompany() {
        return company;
    }

    public void setCompany(Boolean company) {
        this.company = company;
    }
}
