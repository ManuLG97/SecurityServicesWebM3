package securityservices.core.equipment.domain.model;

import java.util.UUID;
import securityservices.core.shared.products.Storable;
import securityservices.core.shared.products.PhysicalProduct;
import securityservices.core.shared.data.PhysicalData;
import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public class Equipment extends PhysicalProduct implements Storable {

    protected UUID equipmentId;
    protected String function, components;
    protected Integer power;

    public Equipment() {
        this.setEquipmentId();
    }

    public Equipment getInstance() {
        return new Equipment();
    }

    public static ResultRequest<Equipment> getInstance(String code, String name, String type, String maker, String description,
            Double price, Double high, Double wide, Double deep, Double weight, Boolean fragile,
            String function, String components, Integer power) {
        Equipment equipment = new Equipment();

        ResultRequest<PhysicalData> physicRequest = PhysicalData.getInstance(high, wide, deep, weight, fragile);

        if (physicRequest.failed()) {
            return ResultRequest.fails(physicRequest.getError());
        }
        equipment.setPhysics(physicRequest.getValue());

        ResultRequest result = equipment.setCode(code);
        if (result.failed()) {
            return result;
        }

        result = equipment.setName(name);
        if (result.failed()) {
            return result;
        }

        result = equipment.setType(type);
        if (result.failed()) {
            return result;
        }

        result = equipment.setMaker(maker);
        if (result.failed()) {
            return result;
        }

        result = equipment.setDescription(description);
        if (result.failed()) {
            return result;
        }

        result = equipment.setPrice(price);
        if (result.failed()) {
            return result;
        }

        result = equipment.setHigh(high);
        if (result.failed()) {
            return result;
        }

        result = equipment.setWide(wide);
        if (result.failed()) {
            return result;
        }

        result = equipment.setDeep(deep);
        if (result.failed()) {
            return result;
        }

        result = equipment.setWeight(weight);
        if (result.failed()) {
            return result;
        }

        result = equipment.isFragile(fragile);
        if (result.failed()) {
            return result;
        }

        result = equipment.setFunction(function);
        if (result.failed()) {
            return result;
        }

        result = equipment.setComponents(components);
        if (result.failed()) {
            return result;
        }

        result = equipment.setPower(power);
        if (result.failed()) {
            return result;
        }

        return ResultRequest.done(equipment);

    }

    public String getEquipmentId() {
        return equipmentId.toString();
    }

    protected void setEquipmentId() {
        this.equipmentId = UUID.randomUUID();
    }

    public String getFunction() {
        return function;
    }

    public ResultRequest setFunction(String function) {
        if (function == null || function.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid function\"");
        }
        this.function = function;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getComponents() {
        return components;
    }

    public ResultRequest setComponents(String components) {
        if (components == null || components.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid components\"");
        }
        this.components = components;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public Integer getPower() {
        return power;
    }

    public ResultRequest setPower(Integer power) {
        if (power == null || power <0 || power == 0) {
            return ResultRequest.fails("\"Error\":\"invalid power\"");
        }
        this.power = power;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    @Override
    public String getDetails() {
        return "Function: " +function+"; Components: "+components;
    }

    @Override
    public String getDimensions() {
        return "High: "+physics.getHigh()+"; Wide"+physics.getWide()+"; Weight"+physics.getWeight();
    }

    @Override
    public Double getVolum() {
        return physics.getHigh() * physics.getWide() * physics.getDeep();
    }

   

}
