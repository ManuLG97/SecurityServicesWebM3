package securityservices.core.shared.products;

import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public abstract class Product implements Marketable {
    protected String code, name, type, maker, description;
    protected double price;
    protected Boolean available;

    public Product() {
        this.available = true;
    }

    public Product(String code, String name, String type, String maker, String description, double price) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.maker = maker;
        this.description = description;
        this.price = price;
        this.available = true;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean isAvailable() {
        return available;
    }

    @Override
    public String getCode() {
        return code;
    }

   public ResultRequest setCode(String code) {
        if (code == null || code.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid code\"");
        }
        this.code = code;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    @Override
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

    public String getType() {
        return type;
    }

    public ResultRequest setType(String type) {
        if (type == null || type.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid type\"");
        }
        this.type = type;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }
    
    public String getMaker() {
        return maker;
    }

    public ResultRequest setMaker(String maker) {
        if (maker == null || maker.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid maker\"");
        }
        this.maker = maker;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getDescription() {
        return description;
    }

    public ResultRequest setDescription(String description) {
        if (description == null || description.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid description\"");
        }
        this.description = description;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    @Override
    public Double getPrice() {
        return price;
    }

     public ResultRequest setPrice(Double price) {
        if (price == null || price <0 || price == 0) {
            return ResultRequest.fails("\"Error\":\"invalid price\"");
        }
        this.price = price;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }
}